package org.modelmapper.module.vavr;

import io.vavr.control.Option;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.modelmapper.module.vavr.StubFactory.*;

public class ValueConverterTest {

    private ModelMapper modelMapper;

    @Before
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.registerModule(new VavrModule());
    }

    @Test
    public void testOption() {
        X x = new X();
        Y y = new Y();
        y.status = Boolean.TRUE;
        x.y = Option.of(y);
        XInfo xinfo = modelMapper.map(x, XInfo.class);
        assertThat(xinfo).isNotNull();
        assertThat(xinfo.y).isNotNull();
        assertThat(xinfo.y.get().status).isEqualTo(Boolean.TRUE);
        assertThat(xinfo.y.get()).isInstanceOf(YInfo.class);
    }

    @Test
    public void testNormalList() {
        final DestList destList = modelMapper.map(stubSourceList(), DestList.class);
        checkListResult(destList);
    }

    @Test
    public void testDerivedList() {
        final DestList destList = modelMapper.map(stubSourceList(), ExtendedDestList.class);
        checkListResult(destList);
    }

    private void checkListResult(DestList destList) {
        assertThat(destList.list.get(1))
                .isNotNull()
                .isInstanceOf(Dest.class);
        assertTrue(destList.list.get(1).x == 5);
    }


    @Test
    public void testNormalArray() {
        final DestArray destArray = modelMapper.map(stubSourceArray(), DestArray.class);
        checkArrayResult(destArray);
    }

    @Test
    public void testDerivedArray() {
        ExtendedDestArray extendedDestArray = modelMapper.map(stubSourceArray(), ExtendedDestArray.class);
        checkArrayResult(extendedDestArray);
    }

    private void checkArrayResult(final DestArray destArray) {
        assertThat(destArray.array.get(1))
                .isNotNull()
                .isInstanceOf(Dest.class);
        assertTrue(destArray.array.get(1).x == 5);
    }

    @Test
    public void testNormalSet() {
        final DestSet destSet = modelMapper.map(stubSourceSet(), DestSet.class);
        checkSetResult(destSet);
    }

    @Test
    public void testDerivedSet() {
        ExtendedDestSet destSet = modelMapper.map(stubSourceSet(), ExtendedDestSet.class);
        checkSetResult(destSet);
    }

    private void checkSetResult(final DestSet destSet) {
        assertThat(destSet.set.head())
                .isNotNull()
                .isInstanceOf(Dest.class);
        assertTrue(destSet.set.head().x == 5);
    }
}

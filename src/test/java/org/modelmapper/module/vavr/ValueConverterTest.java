package org.modelmapper.module.vavr;

import io.vavr.control.Option;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
    public void test() {
        X x = new X();
        Y y = new Y();
        y.status = Boolean.TRUE;
        x.y = Option.of(y);
        XInfo xinfo = modelMapper.map(x, XInfo.class);
        assertNotNull(xinfo);
        assertNotNull(xinfo.y);
        assertThat(xinfo.y.get().status, is(Boolean.TRUE));
        assertThat(xinfo.y.get(), instanceOf(YInfo.class));
    }

    @Test
    public void testNormalList() {
        DestList destList = modelMapper.map(stubSourceList(), DestList.class);
        checkListResult(destList);
    }

    @Test
    public void testDerivedList() {
        ExtendedDestList extendedDestList = modelMapper.map(stubSourceList(), ExtendedDestList.class);
        checkListResult(extendedDestList);
    }

    @Test
    public void testNormalArray() {
        DestArray destArray = modelMapper.map(stubSourceArray(), DestArray.class);
        checkArrayResult(destArray);
    }

    @Test
    public void testDerivedArray() {
        ExtendedDestArray extendedDestList = modelMapper.map(stubSourceArray(), ExtendedDestArray.class);
        checkArrayResult(extendedDestList);
    }

    <T extends DestList> void checkListResult(T mappingResult) {
        assertNotNull(mappingResult.list);
        assertNotNull(mappingResult.list.get(0));
        assertNotNull(mappingResult.list.get(1));
        assertTrue(mappingResult.list.get(0) instanceof Dest);
        assertTrue(mappingResult.list.get(1) instanceof Dest);
        assertTrue(mappingResult.list.get(0).x == 2);
        assertTrue(mappingResult.list.get(1).x == 5);
    }

    <T extends DestArray> void checkArrayResult(T mappingResult) {
        assertNotNull(mappingResult.array);
        assertNotNull(mappingResult.array.get(0));
        assertNotNull(mappingResult.array.get(1));
        assertTrue(mappingResult.array.get(0) instanceof Dest);
        assertTrue(mappingResult.array.get(1) instanceof Dest);
        assertTrue(mappingResult.array.get(0).x == 2);
        assertTrue(mappingResult.array.get(1).x == 5);
    }
}

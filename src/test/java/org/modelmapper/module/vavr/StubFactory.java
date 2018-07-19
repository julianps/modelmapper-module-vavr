package org.modelmapper.module.vavr;

import io.vavr.collection.Array;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.Data;

public class StubFactory {

    // source inner
    @Data
    static class Y {
        Boolean status;
    }

    // dest inner
    @Data
    static class YInfo {
        Boolean status;
    }

    // source wrapper
    @Data
    static class X {
        Option<Y> y;
    }

    // dest wrapper
    @Data
    static class XInfo {
        Option<YInfo> y;
    }

    @Data
    static class DestArray {
        Array<Dest> array;
    }

    @Data
    static class SourceArray {
        Array<Source> array;
    }

    static class ExtendedDestArray extends DestArray {
    }

    @Data
    static class DestList {
        List<Dest> list;
    }

    @Data
    static class Dest {
        int x;
    }

    @Data
    static class SourceList {
        List<Source> list;
    }

    @Data
    static class Source {
        int x;
    }

    static class ExtendedDestList extends DestList {
    }

    static SourceList stubSourceList() {
        Source source1 = new Source();
        source1.x = 2;
        Source source2 = new Source();
        source2.x = 5;
        SourceList sourceList = new SourceList();
        sourceList.list = List.of(source1, source2);
        return sourceList;
    }

    static SourceArray stubSourceArray() {
        Source source1 = new Source();
        source1.x = 2;
        Source source2 = new Source();
        source2.x = 5;
        SourceArray sourceArray = new SourceArray();
        sourceArray.array = Array.of(source1, source2);
        return sourceArray;
    }
}

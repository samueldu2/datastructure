package com.samueldu.newfeatures;

import java.util.function.Function;
import java.util.function.Supplier;

public class Functions {

    public String function(int i){
        Function<Integer, String> intToString = Object::toString;
        Function<String, String> quote = s -> "'" + s + "'";

        Function<Integer, String> quoteIntToString = quote.compose(intToString);
        return quoteIntToString.apply(i);
    }

    public String suppliers(){
        Supplier<String> supplier =()->{
            return "supplied string "+Math.random()*1000;
        };
        return supplier.get();
    }

}

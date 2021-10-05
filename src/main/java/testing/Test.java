package testing;

import commands.rollcube.CubeParser;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        String input = "/r 5d20-20";
//                  /r 1d20
//        Aloija rolled 10
//        Aloija rolled 10
//
//                /r 10d20
//        Ошибка команды
//        Aloija rolled (10+11+2+8+9+10+10+10+10+10)=90
//
//                /r 5d20+100
//        Aloija rolled (3+19+20+7+18)+100=167
//        Aloija rolled (3+19+20+7+18)+100=167
//
//                /r 5d20++20
//        Aloija rolled (36(16+20)+29(9+20)+31(11+20)+22(2+20)+21(1+20))=139
//        Aloija rolled (36(16+20)+29(9+20)+31(11+20)+22(2+20)+21(1+20))=139
//
//                /r 5d20-100
//        Aloija rolled (10+11+9+20+5)-100=-45
//        Aloija rolled (10+11+9+20+5)-100=1
//
//                /r 5d20-20
//        Ошибка команды
//        (1(16-20)+1(9-20)+1(11-20)+1(2-20)+1(1-20))=5

        System.out.println(new CubeParser(input, "/r").roll());

    }
}

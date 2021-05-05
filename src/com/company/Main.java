package com.company;

import com.company.adaptor.*;
import com.company.decorator.*;
import com.company.proxy.aop.AopBrowser;
import com.company.proxy.cache.Browser;
import com.company.proxy.cache.BrowserProxy;
import com.company.proxy.IBrowser;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {
//        ClazzA clazzA = new ClazzA();
//        ClazzB clazzB = new ClazzB();
//
//        //싱글톤 방식으로 생성되는 객체를 참조하기 떄문에
//        //ClientA와 B는 같은 객체를 참조하고 있다.
//        SocketClient ClientA = clazzA.getSocketClient();
//        SocketClient ClientB = clazzB.getSocketClient();
//
//        //true가 나온다
//        System.out.println(ClientA.equals(ClientB));


        //adapter pattern

        //드라이기 110V
        HairDryer hairDryer = new HairDryer();
        //청소기 220v
        Cleaner cleaner = new Cleaner();
        //에어컨 220v
        AirConditioner airConditioner = new AirConditioner();

        connect(hairDryer);
        //청소기는 220v를 구현하기 떄문에 연결되지 않는다.
//        connect(cleaner);
        //어뎁터에 청소기를 연결함
        //중간에 자기자신의 상태는 변환시키지 않고 인터페이스 형식을
        //맞추는 형식을 adapter pattern 이라 한다.
        Electronic110V adapter = new SocketAdapter(cleaner);
        //연결이 된다.
        connect(adapter);

        Electronic110V airAdapter = new SocketAdapter(airConditioner);

        connect(airAdapter);


        //Proxy pattern(cache기능을 통한 예제)
        long startTime = System.currentTimeMillis();
        Browser browser = new Browser("www.naver.com");
        //똑같은 url을 통해 똑같은 html을 반복해서 보여주고 있다.
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        System.out.println(System.currentTimeMillis()-startTime);


        System.out.println();
        long startTime2 = System.currentTimeMillis();
        IBrowser browserProxy = new BrowserProxy("www.naver.com");

        //한번만 로딩되고 다음부터는 캐싱된 html을 보여준다.
        browserProxy.show();
        browserProxy.show();
        browserProxy.show();
        browserProxy.show();
        browserProxy.show();
        System.out.println(System.currentTimeMillis()-startTime2);
        
        //Proxy pattern (Spring AOP를 통한 예시)

        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();

        IBrowser aopBrowser = new AopBrowser("www.naver.com",
                ()->{
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                ()->{
                    long now = System.currentTimeMillis();
                    end.set(now - start.get());
                });
        aopBrowser.show();
        System.out.println("loading time: "+end.get());

        aopBrowser.show();
        System.out.println("loading time: "+end.get());


        //decorator patter

        ICar audi = new Audi(1000);
        audi.showPrice();


        //a3
        ICar audi3 = new A3(audi,"A3");
        audi3.showPrice();

        //a4
        ICar audi4 = new A4(audi,"a4");
        audi4.showPrice();

        //a5
        ICar audi5 = new A5(audi,"a5");
        audi5.showPrice();
    }

    //콘센트
    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    };
}

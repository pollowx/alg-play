package com.hbx.play.zcytest.other;

/**
 * @Auther: bingxin
 * @Date: 2019-10-22 16:44
 * @Description:
 */
public class OtherPot {

    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 10000; i++) {
            if (getRadom7ByRandom5() == 1) {
                count++;
            }
        }
        System.out.println(count);
        System.out.println((double) count / 10000);
        System.out.println(1.0 / 7);
    }

    /**
     * 产生1 - 7的随机数
     * @return
     */
    public static int getRadom7ByRandom5() {
        int num = 0;
        do {
            num = (rand1To5() - 1) * 5 + rand1To5() - 1;
        } while (num > 20);
        return num % 7 + 1;
    }

    public static int rand1To5() {
        return (int) (Math.random() * 5) + 1;
    }



}

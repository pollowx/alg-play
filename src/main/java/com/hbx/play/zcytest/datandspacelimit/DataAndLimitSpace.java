package com.hbx.play.zcytest.datandspacelimit;

import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

/**
 * @Auther: bingxin
 * @Date: 2019-10-14 21:32
 * @Description:
 */
public class DataAndLimitSpace {

    // 布隆过滤器
    // K个hash函数，Hash(varchar) ->
    // k组hash的结果，如果某个对应的hash值不存在那么改原始值一定不在样本中
    // k组hash的结果，如果某个对应的hash值存在那么改原始值不一定在样本中（是概率问题）

    public static void main(String[] args) {
        long size = 1000 * 1000;

        BloomFilter<Integer> filter = BloomFilter.create(new Funnel<Integer>() {
            @Override
            public void funnel(Integer from, PrimitiveSink into) {
                return;
            }
        }, size);

        for (int i = 0; i <= size; i++) {
            if (i != 59345) {
                filter.put(i);
            }
        }
        System.out.println(filter.mightContain(1000000000));
    }

    // 2GB限制找到20亿中出现次数最多的数字
    // 文件分割，这种考察大数据量的一般都是文件分割，或者机器分割来处理分割后的小文件
    // 单个文件统计后再merge合并

    // 40亿个文件中找到未出现的数字
    // 这种找未出现的数字一般有空间要求的，都是bitmap，按照每一位来统计是否存在
    // 如果是只用找到一个那么先分割文件，如果是有顺序的，
    // 每一个等分文件的数字数量应该是一样的，找到至少有小于该数量的文件，在这个文件中找到一个未出现的数字即可

    // 100亿URL中 找到重复的URL, 以及TopK问题
    // 查看那些资源有限制，然后分割文件，处理，topK也是分割文件然后再处理

    // 40亿个非负整数中找到出现两次的数字，和所有数字的中位数
    // 出现两次或者是次数的问题一般都是用bitmap, 多次的话可以用多个bit来统计
    // 中位数这个先分区，然后找到第20亿在哪一个区间上，然后循环整个数据，来统计词频，第N个就是中位数

    // 一致性hash的远离



}

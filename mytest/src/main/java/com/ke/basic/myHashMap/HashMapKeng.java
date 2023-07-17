package com.ke.basic.myHashMap;




import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * @auther: Xudong Zhang
 * @create: 2020/5/15 09:11
 * @description:
 */
public class HashMapKeng {
    @Test
    public void test(){
        /**
         * 功能描述:添加对象想要覆盖出现问题
         * 修改了对象的内容没有修改引用，出现hashcode不一致的情况
         * https://mp.weixin.qq.com/s/8M-XDxwiSGShVoNiHpV6eQ，其他的坑详见链接
         * 总之，尽量少用自定义的对象作为key，如果使用了则记得重写hashcode方法，并设置对象为不可变
         */
        HashMap<Goods, Integer> map = new HashMap<>();
        Goods goods1 = new Goods(60, "测试", 66);
        System.out.println(goods1.hashCode());
        map.put(goods1,1);

        goods1= new Goods(66, "测试2", 90);
        map.put(goods1,88);
        System.out.println(goods1.hashCode());

        System.out.println(map.size());


    }


    public static class Goods{
        public int id;
        public String name;
        public int amount;

        public Goods(int id, String name, int amount) {
            this.id = id;
            this.name = name;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAmount() {
            return amount;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
}

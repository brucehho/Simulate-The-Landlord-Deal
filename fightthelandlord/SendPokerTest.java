package fightthelandlord;

import java.util.*;

/**
 * @author hungban
 * @create 2021-01-03 17:25
 */
public class SendPokerTest {
    public static void main(String[] args) {
        // カートを買います
        // 定义 一个双列 集合， key = number, value = card 规则： 编号越小，牌越小！
        Map<Integer, String> pokers = new HashMap<>();

        //定义一个单列集合， 存储所有牌的numbers/
        List<Integer> list = new ArrayList<>();

        //买牌 动作。
        /*pokers.put(0,"♠3");
        pokers.put(0,"❤3");
        pokers.put(0,"♣3");
        ...
        普通牌 52.
        */
        String[] colors = {"♠", "❤", "♣", "♦"};
        String[] numbers = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        int num = 0; //表示牌的编号
        //循环嵌套， 造 普通牌
        for (String number : numbers) {//外循环，获取所有的点数。
            for (String color : colors) { //内循环，获取所有的花色.
                String poker = color + number;
                // System.out.println(poker);

                // 将牌的编号， 具体牌放入 双列集合中。
                pokers.put(num, poker);
                //将牌的编号， 放到单列集合中。
                list.add(num);
                //每添加一张牌，编号自增1.
                num++;
            }
        }
        // 大小王
        //添加小王
        pokers.put(num, "小鬼");
        list.add(num++);
        //添加大王
        pokers.put(num, "大鬼");
        list.add(num);

        System.out.println("所有的牌： " + pokers);
        System.out.println("牌的编号： " + list);

        //2 . 洗牌！
        Collections.shuffle(list);
        System.out.println("洗牌之后，编号为: " + list);

        //3.1 定义4个集合， 3名玩家，底牌。
        List<Integer> player1 = new ArrayList<>();
        List<Integer> player2 = new ArrayList<>();
        List<Integer> player3 = new ArrayList<>();
        List<Integer> hold = new ArrayList<>();

        //3.2 具体 发牌动作， 索引 和 3 取模， 决定发给谁。
        for (int i = 0; i < list.size(); i++) {
            //获取编号
            Integer pokerNum = list.get(i);
            //System.out.println(pokerNum);
            if (i >= list.size() - 3) {
                //底牌
                hold.add(pokerNum);
            } else if (i % 3 == 0) {
                player1.add(pokerNum);
            } else if (i % 3 == 1) {
                player2.add(pokerNum);
            } else if (i % 3 == 2) {
                player3.add(pokerNum);
            }

        }
        //3.3 查看玩家，底牌的编号。
        /*System.out.println("Player1: " + player1);
        System.out.println("Player2: " + player2);
        System.out.println("Player3: " + player3);
        System.out.println("Hold: " + hold);*/

        //4. 查看具体的牌。
        System.out.println("________________");
        /*String str = printPoker(player1,pokers);
        System.out.println("Player1: " + str);*/

        System.out.println("Player1: " + printPoker(player1,pokers));
        System.out.println("Player2: " + printPoker(player2,pokers));
        System.out.println("Player3: " + printPoker(player3,pokers));
        System.out.println("Hold: " + printPoker(hold,pokers));
    }

    // 4 定义一个方法， 用来看牌。 方法名 printpoker 参数列表  返回值String
    public static String printPoker(List<Integer> nums, Map<Integer, String> pokers) {
        //1. 对牌的编号进行升序排序。
        Collections.sort(nums);
        //2. 遍历牌的编号 集合， 获取每一个编号。
        StringBuilder sb = new StringBuilder();
        for (Integer num : nums) {
            //num 查找具体牌的 编号。
            String porker =  pokers.get(num);
            //3. 双列集合中 查找编号对应的具体牌。
            //4. 拼接
            sb.append(porker+" ");
        }
        //5. 最后结果返回。
        String str = sb.toString();
        return str.trim();
    }
}
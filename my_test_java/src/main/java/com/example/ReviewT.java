package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaozhu on 17/5/4.
 */

public class ReviewT {

    public static void main(String[] args) {

    }

    //TODO 泛型-----------

    public static class A<T> {
        //泛型类
    }

    public static class B extends A {
        //非泛型子类
    }

    public static class C<T> extends A {
        //泛型子类
    }

    public static class D<T> extends A<T> {
        //泛型子类
    }


    public static class E<T> {
        public T fun1() {//非泛型方法
            return null;
        }

        public void fun2(T t) {//非泛型方法

        }

        public <e> e fun3() {//泛型方法
            return null;
        }

        public <e> void fun3(e e) {//泛型方法

        }

    }

    //TODO 通配符------无限,上限,下限-----

    public void fun2() {
        String[] arr2 = new String[10];
        List<String> list2 = new ArrayList<>();
    }

    public void fun3() {
        Object[] arr3 = new String[10];
        arr3[0] = new Integer(100);//编程不报错，运行报：ArrayStoreException

        //报错
//        List<Object> list3 = new ArrayList<String>();
    }

    //TODO 通配符----无限:(集合 的[参数为泛型的方法] 和 [返回值为泛型的方法])都不能使用了----------------------------------------------------------------
    public void fun4() {
        List<Integer> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        //print1(list1); //报错
        //print1(list2); //报错
        // 因为有一个实参向形参赋值的过程，编译器直接不让通过 List<Object> list= intList=new ArrayList<Integer>();

        //TODO 思考：那每个不同类型的集合都需要不同的打印方法，那方法是也太多了，所以就有了通配符的出现
        //这样就可以使用通用的打印方法了
        print2(list1);
        print2(list2);
    }

    public void print1(List<Object> list) {
    }

    public void print2(List<?> list) {
        /**
         * 思考：无限通配符,虽然都可以调用了，但是却带来了一些参数使用上面的限制
         */
        //list.add(new Integer(100));//报错，因为并不知道传递进来的到底是上面，如果是String,那编程通过就笑话了，add()作废

        Object obj = list.get(0);//其实这个参数可以使用的原因是因为Object为所有类的父类，不让这个get()方法也作废

        /**
         * 小结:
         *      1、当使用通配符时,对泛型类中的参数为泛型类型的方法起到了副作用，不能再使用！
         *      比如上面，List.add(E e)，List<E>为泛型类，add()方法的参数为泛型类型，所以add()方法不能使用了
         *      2、当使用通配符时，泛型类中返回值为泛型类型的方法，也作废了！
         *      注意上面，虽然Object obj = list.get(0)，可以使用，但是其实也相当于作废了，因为list.get()方法的返回值也是泛型类型
         * 通配符的好处：可以使泛型类型更加通用！尤其是在方法调用时形参使用通配符！
         */
    }

    //TODO 通配符----上限:子类统配:[返回值为泛型的方法]可以使用----------------------------------------------------------------
    public void fun5() {
        List<Integer> intList = new ArrayList<>();
        List<Long> longList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        List<Object> objects = new ArrayList<>();

        print3(intList); //正确,通配符上限：子类统配
        print3(longList); //正确,通配符上限：子类统配
        print3(numberList); //正确,通配符上限：子类统配
        //print3(objects); //报错，因为List<T>中,T现在为Object类型,不是Number类的子类,所以不能传参

        //TODO 注意这里，和上转型对象的区别
        //print31(intList); //报错，参数类型不对,注意这里和对象上转型不同
        //print31(longList); //报错，参数类型不对,注意这里和对象上转型不同
        print31(numberList); //正确,因为传递的就是List<Number>类型
        //print31(objects); //报错，参数类型不对,
    }

    /**
     * 通配符上限：子类统配
     * 子类统配，必须是Number及Number的子类才可以传参
     * 这样的缺点是：降低了参数的灵活性，但是关闭一扇大门就会打开一扇大门
     * 因为（可以传参的类,即?所代表的类型）都是Number的子类，所有返回值可以使用Number来接受，get()方法获得解放，即返回值为泛型的方法可以使用了
     */
    public void print3(List<? extends Number> list) {
        Number number = list.get(0);//正确
        //list.add(new Integer(100));/但add()方法还是被废，因为不知道具体传入的哪一个子类，如果传入的是Long，加入Integer就笑话了
    }

    public void print31(List<Number> list) {
        //TODO 注意这里，和上转型对象的区别
    }

    //TODO 通配符----下限:父类统配:[参数为泛型的方法]可以使用----------------------------------------------------------------
    public void fun6() {
        List<Integer> intList = new ArrayList<>();
        List<Long> longList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();

        print4(intList);//正确,通配符下限：父类统配
        //print4(longList); //报错，Long并不是Integer的父类
        print4(numberList); //正确,通配符下限：父类统配
        print4(objectList); //正确,通配符下限：父类统配,因为Object是所有类的父类
    }

    /**
     * 通配符下限：父类统配
     * 父类统配，必须是Integer及Integer的父类才可以传参
     * 这样的缺点是：降低了参数的灵活性，但是关闭一扇大门就会打开一扇大门
     * 好处是因为（可以传参的类,即?所代表的类型）都是Integer的父类，[参数为泛型的方法]都可以使用了
     * 但是相反的，返回值为泛型类型的方法就不能使用，因为子类不能接收父类的值
     */
    public void print4(List<? super Integer> list) {
        list.add(new Integer(100)); //正确
        //Integer integer = list.get(0);//报错,返回值为泛型的方法就不能使用了,因为不知道返回的类型是什么,子类不能接收父类的值
    }


    /**
     * 注意，这是上转型
     */
    public void fun7() {
        Integer integer = new Integer(100);
        Long aLong = new Long(1000);

        print5(integer);
        print5(aLong);
    }

    public void print5(Number number) {
    }

    /**
     * 注意，通配符 ? 只能作为泛型参数使用
     */
//    public void print6(? extends Number) {
//    }

}

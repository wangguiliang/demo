package com.wgl.demo.mock;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.verification.NoInteractionsWanted;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Description:https://blog.csdn.net/xiang__liu/article/details/81147933
 *
 * @author: wangguiliang
 * @date:2019-09-22
 */
public class MockTest {


    @Test
    public void test1() {
        List mock = mock(List.class);
        assertEquals(mock.get(0), null);
    }

    /**
     * 在创建mock对象时，有的方法我们没有进行stubbing，
     * 所以调用时会放回Null这样在进行操作是很可能抛出NullPointerException。
     * 如果通过RETURNS_SMART_NULLS参数创建的mock对象在没有调用stubbed方法时会返回SmartNull。
     * 例如：返回类型是String，会返回"";
     * 是int，会返回0；
     * 是List，会返回空的List。
     * 另外，在控制台窗口中可以看到SmartNull的友好提示。
     */
    @Test
    public void test2() {
        // 这样就不会返回null了。
        List mock = mock(List.class, RETURNS_SMART_NULLS);
        System.out.println(mock.get(0));
        System.out.println(mock.toArray().length);

    }

    /**
     * RETURNS_DEEP_STUBS参数程序会自动进行mock所需的对象，
     * 方法deepstubsTest和deepstubsTest2是等价的
     */
    @Test
    public void test3() {

        // 相当于递归存根了
        Person person = mock(Person.class, RETURNS_DEEP_STUBS);
        when(person.getTeacher().getDestination()).thenReturn("yuwen");
        person.getTeacher().getDestination();
        // 这里犯了错误，per.getTeacher是verfy的参数
        verify(person.getTeacher()).getDestination();
        assertEquals("yuwen", person.getTeacher().getDestination());


        // 上面等同于麻烦的下面
        Person person1 = mock(Person.class);
        Teacher teacher = mock(Teacher.class);

        when(person1.getTeacher()).thenReturn(teacher);
        when(teacher.getDestination()).thenReturn("yuwen");

        person1.getTeacher().getDestination();
        verify(person1.getTeacher()).getDestination();

        assertEquals("yuwen", person1.getTeacher().getDestination());
    }

    /**
     * 模拟抛异常
     */
    @Test(expected = RuntimeException.class)
    public void test4() {
        List list = mock(List.class);
        doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }

    @Test(expected = RuntimeException.class)
    public void test5() {

        List list = mock(List.class);
        when(list.add(1)).thenThrow(RuntimeException.class);
        list.add(1);
    }


    @Mock
    private List mockList;

    @Test
    public void test6() {

        // 初始化mock。或者使用@RunWith(MockitoJUnitRunner.class)
        MockitoAnnotations.initMocks(this);
        // 不能直接用，要初始化
        mockList.add(1);
        verify(mockList).add(1);
    }

    /**
     * 根据不同参数匹配不同结果
     */
    @Test
    public void test7() {
        Comparable comparable = mock(Comparable.class);
        when(comparable.compareTo("Test")).thenReturn(1);
        when(comparable.compareTo("Omg")).thenReturn(2);

        assert 1 == comparable.compareTo("Test");
        assert 2 == comparable.compareTo("Omg");

        assert 0 == comparable.compareTo("Not stub");
    }

    /**
     * 也可匹配任何参数
     * 及对参数的过滤
     */
    @Test
    public void test8() {
        List list = mock(List.class);
        //匹配任意参数
        when(list.get(anyInt())).thenReturn(1);
        when(list.contains(argThat(new IsValid()))).thenReturn(true);
        assertEquals(1, list.get(1));
        assertEquals(1, list.get(999));
        assertTrue(list.contains(1));
        assertTrue(!list.contains(3));

    }

    /**
     * 参数的过滤
     */
    private class IsValid implements ArgumentMatcher<Integer> {
        @Override
        public boolean matches(Integer o) {
            return o == 1 || o == 2;
        }
    }

    /**
     * 如果你使用了参数匹配，那么所有的参数都必须通过matchers来匹配，如下代码：
     */
    @Test
    public void all_arguments_provided_by_matchers() {
        Comparator comparator = mock(Comparator.class);
        comparator.compare("nihao", "hello");
        //如果你使用了参数匹配，那么所有的参数都必须通过matchers来匹配
        verify(comparator).compare(anyString(), eq("hello"));
        //下面的为无效的参数匹配使用
        //verify(comparator).compare(anyString(),"hello");
    }

    /**
     * 和上面一样是参数匹配
     * 例子：参数为2个值时才匹配
     * 以下测试结果失败，会把失败原因详细打印出来
     */
    @Test
    public void argumentMatchersTest() {
        //创建mock对象
        List<String> mock = mock(List.class);

        //argThat(Matches<T> matcher)方法用来应用自定义的规则，可以传入任何实现Matcher接口的实现类。
        when(mock.addAll(argThat(new IsListofTwoElements()))).thenReturn(true);

        mock.addAll(Arrays.asList("one", "two", "three"));
        //IsListofTwoElements用来匹配size为2的List，因为例子传入List为三个元素，所以此时将失败。
        verify(mock).addAll(argThat(new IsListofTwoElements()));
    }

    class IsListofTwoElements implements ArgumentMatcher<List> {
        public boolean matches(List list) {
            return list.size() == 2;
        }
    }


    /**
     * 上面的参数匹配器会降低代码可读性。可以使用参数捕获器。
     */
    @Test
    public void capturing_args() {
        // mock一个PersonDao的实现类。实际上没有实现类
        PersonDao personDao = mock(PersonDao.class);
        PersonService personService = new PersonService(personDao);

        // 捕获参数用这个，需要提前构造，然后在verify里面获取。
        ArgumentCaptor<Person1> argument = ArgumentCaptor.forClass(Person1.class);
        personService.update(1, "jack");

        // 在这里捕获参数，捕获一个Person，只能捕获执行一次的参数，执行多次会出错。
        verify(personDao).update(argument.capture());

        assertEquals(1, argument.getValue().getId());
        assertEquals("jack", argument.getValue().getName());

        personService.update(2, "mark");

        // 多次捕获，需要加times。获取的值是最后一次的值。
        verify(personDao, times(2)).update(argument.capture());
        assertEquals("mark", argument.getValue().getName());
        // 由于上面获取一次参数了。这次有获取，因此一共有3个参数。
        assertArrayEquals(new Object[]{"jack", "jack", "mark"}, argument.getAllValues().stream().map(v -> {
            return v.name;
        }).toArray());
    }

    class Person1 {
        private int id;
        private String name;

        Person1(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    interface PersonDao {
        public void update(Person1 person1);
    }

    class PersonService {
        private PersonDao personDao;

        PersonService(PersonDao personDao) {
            this.personDao = personDao;
        }

        public void update(int id, String name) {
            personDao.update(new Person1(id, name));
        }
    }


    /**
     * 不仅参数可以配置。返回值也可以配置！
     * 使用方法预期回调接口生成期望值（Answer结构）
     */
    @Test
    public void answerTest() {
        when(mockList.get(anyInt())).thenAnswer(new CustomAnswer());
        assertEquals("hello world:0", mockList.get(0));
        assertEquals("hello world:999", mockList.get(999));
    }

    private class CustomAnswer implements Answer<String> {
        @Override
        public String answer(InvocationOnMock invocation) throws Throwable {
            Object[] args = invocation.getArguments();
            return "hello world:" + args[0];
        }
    }

    /**
     * 也可使用匿名内部类实现
     */
    @Test
    public void answer_with_callback() {
        //使用Answer来生成我们我们期望的返回
        when(mockList.get(anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return "hello world:" + args[0];
            }
        });
        assertEquals("hello world:0", mockList.get(0));
        assertEquals("hello world:999", mockList.get(999));
    }


    /**
     * 修改对未预设的调用返回默认期望
     */
    @Test
    public void unstubbed_invocations() {
        //mock对象使用Answer来对未预设的调用返回默认期望值
        List mock = mock(List.class, new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return 999;
            }
        });
        //下面的get(1)没有预设，通常情况下会返回NULL，但是使用了Answer改变了默认期望值
        assertEquals(999, mock.get(1));
        //下面的size()没有预设，通常情况下会返回0，但是使用了Answer改变了默认期望值
        assertEquals(999, mock.size());
    }

    /**
     * Mock不是真实的对象，它只是用类型的class创建了一个虚拟对象，并可以设置对象行为
     * Spy是一个真实的对象，但它可以设置对象行为
     * InjectMocks创建这个类的对象并自动将标记@Mock、@Spy等注解的属性值注入到这个中
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void spy_on_real_objects() {
        List list = new LinkedList();
        List spy = spy(list);

        //下面预设的spy.get(0)会报错，因为会调用真实对象的get(0)，所以会抛出越界异常
        //when(spy.get(0)).thenReturn(3);

        //使用doReturn-when可以避免when-thenReturn调用真实对象api
        //我终于明白了，先doReturn，可以避免真的执行出错误。
        doReturn(999).when(spy).get(999);
        assertEquals(999, spy.get(999));

        //预设size()期望值
        when(spy.size()).thenReturn(100);
        assertEquals(100, spy.size());

        //调用真实对象的api
        spy.add(1);
        spy.add(2);
        assertEquals(1, spy.get(0));
        assertEquals(2, spy.get(1));

        verify(spy).add(1);
        verify(spy).add(2);
        System.out.println(spy.get(999));
        System.out.println(spy.get(1));

        // 一下2个不存在，没有报错，后面的999就被吃了。。怪
        System.out.println("2" + spy.get(2));
        System.out.println("3" + spy.get(3));
        System.out.println(spy.get(999));
    }


    /**
     * 真实的部分mock
     * 没想到，mock也可以调用真实api
     */
    @Test
    public void real_partial_mock() {
        //通过spy来调用真实的api
        List list = spy(new ArrayList());
        assertEquals(0, list.size());
        A a = mock(A.class);
        //通过thenCallRealMethod来调用真实的api
        when(a.doSomething(anyInt())).thenCallRealMethod();
        assertEquals(999, a.doSomething(999));
    }


    class A {
        public int doSomething(int i) {
            return i;
        }
    }


    /**
     * 没想到mock还可以重置，可以反复利用！
     */
    @Test
    public void reset_mock() {
        List list = mock(List.class);
        when(list.size()).thenReturn(10);
        list.add(1);
        assertEquals(10, list.size());
        //重置mock，清除所有的互动和预设
        reset(list);
        assertEquals(0, list.size());
    }


    /**
     * 验证确切的调用次数
     */
    @Test
    public void verifying_number_of_invocations() {
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);
        //验证是否被调用一次，等效于下面的times(1)
        verify(list).add(1);
        verify(list, times(1)).add(1);
        //验证是否被调用2次
        verify(list, times(2)).add(2);
        //验证是否被调用3次
        verify(list, times(3)).add(3);
        //验证是否从未被调用过
        verify(list, never()).add(4);
        //验证至少调用一次
        verify(list, atLeastOnce()).add(1);
        //验证至少调用2次
        verify(list, atLeast(2)).add(2);
        //验证至多调用3次
        verify(list, atMost(3)).add(3);
    }


    /**
     * 连续调用
     */
    @Test(expected = RuntimeException.class)
    public void consecutive_calls() {
        //模拟连续调用返回期望值，如果分开，则只有最后一个有效
        when(mockList.get(0)).thenReturn(0);
        when(mockList.get(0)).thenReturn(1);
        // 上面两个被覆盖了，只有下面这个有效
        when(mockList.get(0)).thenReturn(2);

        // 下面这个很重要，支持连续调用，每一次都是有效的，也就是多个返回值。那有没有多个输入呢？
        when(mockList.get(1)).thenReturn(0).thenReturn(1).thenThrow(new RuntimeException());
        assertEquals(2, mockList.get(0));
        assertEquals(2, mockList.get(0));
        assertEquals(2, mockList.get(0));
        assertEquals(0, mockList.get(1));
        assertEquals(1, mockList.get(1));
        //第三次或更多调用都会抛出异常
        mockList.get(1);
    }


    /**
     * 验证执行顺序
     * 多个不同mock的执行顺序
     */
    @Test
    public void verification_in_order() {
        List list = mock(List.class);
        List list2 = mock(List.class);
        list.add(1);
        list2.add("hello");
        list.add(2);
        list2.add("world");
        //将需要排序的mock对象放入InOrder
        InOrder inOrder = inOrder(list, list2);
        //下面的代码不能颠倒顺序，验证执行顺序
        inOrder.verify(list).add(1);
        inOrder.verify(list2).add("hello");
        inOrder.verify(list).add(2);
        inOrder.verify(list2).add("world");
    }


    /**
     * 确保模拟对象上无互动发生
     * 就是确保mock对象没有操作过，只是新建过
     */
    @Test
    public void verify_interaction() {
        List list = mock(List.class);
        List list2 = mock(List.class);
        List list3 = mock(List.class);
        list.add(1);
        verify(list).add(1);
        verify(list, never()).add(2);
        //验证零互动行为
        verifyZeroInteractions(list2, list3);
    }


    /**
     * 找出冗余的互动(即未被验证到的)
     */
    @Test(expected = NoInteractionsWanted.class)
    public void find_redundant_interaction() {
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        verify(list, times(2)).add(anyInt());
        //检查是否有未被验证的互动行为，因为add(1)和add(2)都会被上面的anyInt()验证到，所以下面的代码会通过
        verifyNoMoreInteractions(list);

        List list2 = mock(List.class);
        list2.add(1);
        list2.add(2);
        verify(list2).add(1);
        //检查是否有未被验证的互动行为，因为add(2)没有被验证，所以下面的代码会失败抛出异常
        verifyNoMoreInteractions(list2);
    }


}

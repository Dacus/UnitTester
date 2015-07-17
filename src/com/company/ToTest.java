package com.company;

/**
 * Created by intern on 7/17/15.
 */
public class ToTest {
    @Before
    public void BeforeTest() {
        System.out.println("Before");
    }

    @Test
    public void Test1() {
        System.out.println("Test1");
    }

    @Test
    public void Test2() {
        System.out.println("Test2");
    }

    @After
    public void AfterTest() {
        System.out.println("After");
    }
}

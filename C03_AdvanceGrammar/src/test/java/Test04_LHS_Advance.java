import io.github.coffee330501.entity.Student;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 查询
 * 不需要 fireAll
 */
public class Test04_LHS_Advance {
    KieSession simpleRuleKSession;

    @Before
    public void before() {
        //第一步 获取KieServices
        KieServices kieServices = KieServices.Factory.get();
        //第二步获取kieContainer
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //第三步获取kieSession
        simpleRuleKSession = kieClasspathContainer.newKieSession();
    }

    @Test
    public void test1() {
        Student student1 = new Student();
        student1.setAge(10);
        student1.setName("张三");
        simpleRuleKSession.insert(student1);
        simpleRuleKSession.fireAllRules(new RuleNameEqualsAgendaFilter("lhs_advance_1"));
    }

    @Test
    public void test2() {
        simpleRuleKSession.fireAllRules(new RuleNameEqualsAgendaFilter("lhs_advance_2"));
    }

    @Test
    public void test3() {
        Student student1 = new Student();
        student1.setAge(11);
        student1.setName("张三");

        Student student2 = new Student();
        student2.setAge(11);
        student2.setName("张三");

        simpleRuleKSession.insert(student1);
        simpleRuleKSession.insert(student2);

        simpleRuleKSession.fireAllRules(new RuleNameEqualsAgendaFilter("lhs_advance_3"));
    }

    @Test
    public void test4() {
        Student student1 = new Student();
        student1.setAge(6);
        student1.setName("张三");

        Student student2 = new Student();
        student2.setAge(7);
        student2.setName("李四");

        simpleRuleKSession.insert(student1);
        simpleRuleKSession.insert(student2);

        simpleRuleKSession.fireAllRules(new RuleNameStartsWithAgendaFilter("lhs_advance_4"));
    }

    /**
     * 这里很奇怪
     * 使用了 extends 后 r2 继承 r1
     * 若Fact对象中有一个符合r2，其余的Fact对象只需要符合r1就会触发r2
     * 一个Fact对象是正常的
     * 不使用extends，多个Fact对象也是正常的
     */
    @Test
    public void test5() {
        Student student1 = new Student();
        student1.setAge(12);
        student1.setName("张三");
        simpleRuleKSession.insert(student1);

        Student student2 = new Student();
        student2.setAge(7);
        student2.setName("李四");
        simpleRuleKSession.insert(student2);

        Student student3 = new Student();
        student3.setAge(21);
        student3.setName("王五");
        simpleRuleKSession.insert(student3);

        Student student4 = new Student();
        student4.setAge(21);
        student4.setName("赵六");
        simpleRuleKSession.insert(student4);

        simpleRuleKSession.fireAllRules(new RuleNameStartsWithAgendaFilter("lhs_advance_5"));
    }

    @After
    public void after() {
        //第六步 关闭规则引擎
        simpleRuleKSession.dispose();
        System.out.println("规则执行完成，关闭规则");
    }
}

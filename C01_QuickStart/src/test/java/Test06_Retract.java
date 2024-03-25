import io.github.coffee330501.droolsLearn.entity.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Test06_Retract {
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

    /**
     * 控制只执行某个规则
     */
    @Test
    public void testRule() {
        //新建事实对象
        Student student = new Student();
        student.setId("10");

        //第四步 插入事实对象到session中
        simpleRuleKSession.insert(student);

        //第五步 执行规则引擎
        simpleRuleKSession.fireAllRules();
    }

    @After
    public void after() {
        //第六步 关闭规则引擎
        simpleRuleKSession.dispose();
        System.out.println("规则执行完成，关闭规则");
    }
}

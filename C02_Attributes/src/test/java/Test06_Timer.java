import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 定时器
 * 注意启动方式与结束方式变了
 */
public class Test06_Timer {
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
    public void test() throws InterruptedException {
        new Thread(() -> {
            //启动规则引擎进行规则匹配，直到调用halt方法才结束规则引擎
            simpleRuleKSession.fireUntilHalt(new RuleNameStartsWithAgendaFilter("rule_timer"));
        }).start();
        Thread.sleep(10000);
    }

    @After
    public void after() {
        //第六步 关闭规则引擎
        simpleRuleKSession.halt();
        simpleRuleKSession.dispose();
        System.out.println("规则执行完成，关闭规则");
    }
}

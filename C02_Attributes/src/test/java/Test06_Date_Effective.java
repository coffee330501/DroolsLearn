import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 设置规则生效时间
 * 注意要设置日期格式
 */
public class Test06_Date_Effective {
    KieSession simpleRuleKSession;

    @Before
    public void before() {
        //设置日期格式
        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm");

        //第一步 获取KieServices
        KieServices kieServices = KieServices.Factory.get();
        //第二步获取kieContainer
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //第三步获取kieSession
        simpleRuleKSession = kieClasspathContainer.newKieSession();
    }

    @Test
    public void test() {
        simpleRuleKSession.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_date_effective"));
    }

    @After
    public void after() {
        //第六步 关闭规则引擎
        simpleRuleKSession.dispose();
        System.out.println("规则执行完成，关闭规则");
    }
}

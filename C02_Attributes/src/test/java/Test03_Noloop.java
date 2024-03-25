import io.github.coffee330501.entity.ComparisonOperatorEntity;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Test03_Noloop {
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
    public void test() {
        //新建事实对象
        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        entity.setName("张三");

        //第四步 插入事实对象到session中
        simpleRuleKSession.insert(entity);

        //第五步 执行规则引擎
        simpleRuleKSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_noloop"));
    }

    @After
    public void after() {
        //第六步 关闭规则引擎
        simpleRuleKSession.dispose();
        System.out.println("规则执行完成，关闭规则");
    }
}

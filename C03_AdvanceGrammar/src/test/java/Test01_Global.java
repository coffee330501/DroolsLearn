import io.github.coffee330501.service.UserService;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局变量
 * 使用前记得给session设置全局变量
 */
public class Test01_Global {
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
        //设置全局变量，名称和类型必须和规则文件中定义的全局变量名称对应
        simpleRuleKSession.setGlobal("userService", new UserService());
        simpleRuleKSession.setGlobal("count", 5);
        List<String> list = new ArrayList<>();
        simpleRuleKSession.setGlobal("gList", list);

        simpleRuleKSession.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_global"));
    }

    @After
    public void after() {
        //第六步 关闭规则引擎
        simpleRuleKSession.dispose();
        System.out.println("规则执行完成，关闭规则");
    }
}

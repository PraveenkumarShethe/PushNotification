package praveenkumar.push.notification.PushNotification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.Filter;
import java.io.IOException;

/**
 * Created by Praveenkumar on 3/6/2017.
 */
@Configuration
@ComponentScan(basePackages = "praveenkumar.push.notification.PushNotification")
@EnableJpaRepositories("praveenkumar.push.notification.PushNotification.repository")
@EnableWebMvc
@ConfigurationProperties(value = "classpath:/application.yml")
public class WebapplicationConfiguration {


    /**
     * In this method
     * Convenient subclass of {@link UrlBasedViewResolver} that supports
     * {@link InternalResourceView} (i.e. Servlets and JSPs) and subclasses
     * such as {@link JstlView}.
     * <p>
     * <p>The view class for all views generated by this resolver can be specified
     * The default is {@link InternalResourceView}, or {@link JstlView} if the
     * JSTL API is present.
     * <p>
     * <p>BTW, it's good practice to put JSP files that just serve as views under
     * WEB-INF, to hide them from direct access (e.g. via a manually entered URL).
     * Only controllers will be able to access them then.
     * <p>
     * <p><b>Note:</b> When chaining ViewResolvers, an InternalResourceViewResolver
     * always needs to be last, as it will attempt to resolve any view name,
     * no matter whether the underlying resource actually exists.
     *
     * @see InternalResourceView
     * @see JstlView
     */

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        return resolver;
    }

    /**
     * Annotation providing a convenient and declarative mechanism for adding a
     * {@link org.springframework.core.env.PropertySource PropertySource} to Spring's
     * {@link org.springframework.core.env.Environment Environment}. To be used in
     * conjunction with @{@link Configuration} classes.
     * <p>
     * <h3>Example usage</h3>
     * <p>
     * <p>Given a file {@code app.properties} containing the key/value pair
     * {@code testbean.name=myTestBean}, the following {@code @Configuration} class
     * uses {@code @PropertySource} to contribute {@code app.properties} to the
     * {@code Environment}'s set of {@code PropertySources}.
     * <p>
     * <pre class="code">
     * &#064;Configuration
     * &#064;PropertySource("classpath:/com/myco/app.properties")
     * public class AppConfig {
     * &#064;Autowired
     * Environment env;
     * <p>
     * &#064;Bean
     * public TestBean testBean() {
     * TestBean testBean = new TestBean();
     * testBean.setName(env.getProperty("testbean.name"));
     * return testBean;
     * }
     * }</pre>
     * <p>
     * Notice that the {@code Environment} object is @{@link
     * org.springframework.beans.factory.annotation.Autowired Autowired} into the
     * configuration class and then used when populating the {@code TestBean} object. Given
     * the configuration above, a call to {@code testBean.getName()} will return "myTestBean".
     * <p>
     * <h3>Resolving ${...} placeholders in {@code <bean>} and {@code @Value} annotations</h3>
     * <p>
     * In order to resolve ${...} placeholders in {@code <bean>} definitions or {@code @Value}
     * annotations using properties from a {@code PropertySource}, one must register
     * a {@code PropertySourcesPlaceholderConfigurer}. This happens automatically when using
     * {@code <context:property-placeholder>} in XML, but must be explicitly registered using
     * a {@code static} {@code @Bean} method when using {@code @Configuration} classes. See
     * the "Working with externalized values" section of @{@link Configuration}'s javadoc and
     * "a note on BeanFactoryPostProcessor-returning @Bean methods" of @{@link Bean}'s javadoc
     * for details and examples.
     * <p>
     * <h3>Resolving ${...} placeholders within {@code @PropertySource} resource locations</h3>
     * resource location} will be resolved against the set of property sources already
     * registered against the environment. For example:
     * <p>
     * <pre class="code">
     * &#064;Configuration
     * &#064;PropertySource("classpath:/com/${my.placeholder:default/path}/app.properties")
     * public class AppConfig {
     * &#064;Autowired
     * Environment env;
     * <p>
     * &#064;Bean
     * public TestBean testBean() {
     * TestBean testBean = new TestBean();
     * testBean.setName(env.getProperty("testbean.name"));
     * return testBean;
     * }
     * }</pre>
     * <p>
     * Assuming that "my.placeholder" is present in one of the property sources already
     * registered, e.g. system properties or environment variables, the placeholder will
     * be resolved to the corresponding value. If not, then "default/path" will be used as a
     * default. Expressing a default value (delimited by colon ":") is optional.  If no
     * default is specified and a property cannot be resolved, an {@code
     * IllegalArgumentException} will be thrown.
     * <p>
     * <h3>A note on property overriding with @PropertySource</h3>
     * <p>
     * In cases where a given property key exists in more than one {@code .properties}
     * file, the last {@code @PropertySource} annotation processed will 'win' and override.
     * <p>
     * For example, given two properties files {@code a.properties} and
     * {@code b.properties}, consider the following two configuration classes
     * that reference them with {@code @PropertySource} annotations:
     * <p>
     * <pre class="code">
     * &#064;Configuration
     * &#064;PropertySource("classpath:/com/myco/a.properties")
     * public class ConfigA { }
     * <p>
     * &#064;Configuration
     * &#064;PropertySource("classpath:/com/myco/b.properties")
     * public class ConfigB { }
     * </pre>
     * <p>
     * The override ordering depends on the order in which these classes are registered
     * with the application context.
     * <pre class="code">
     * AnnotationConfigApplicationContext ctx =
     * new AnnotationConfigApplicationContext();
     * ctx.register(ConfigA.class);
     * ctx.register(ConfigB.class);
     * ctx.refresh();
     * </pre>
     * <p>
     * In the scenario above, the properties in {@code b.properties} will override any
     * duplicates that exist in {@code a.properties}, because {@code ConfigB} was registered
     * last.
     * <p>
     * <p>In certain situations, it may not be possible or practical to tightly control
     * property source ordering when using {@code @ProperySource} annotations. For example,
     * if the {@code @Configuration} classes above were registered via component-scanning,
     * the ordering is difficult to predict. In such cases - and if overriding is important -
     * it is recommended that the user fall back to using the programmatic PropertySource API.
     * See {@link org.springframework.core.env.ConfigurableEnvironment ConfigurableEnvironment}
     * and {@link org.springframework.core.env.MutablePropertySources MutablePropertySources}
     * javadocs for details.
     *
     * @author Chris Beams
     * @author Phillip Webb
     * @see PropertySources
     * @see Configuration
     * @see org.springframework.core.env.PropertySource
     * @see org.springframework.core.env.ConfigurableEnvironment#getPropertySources()
     * @see org.springframework.core.env.MutablePropertySources
     * @since 3.1
     */

    @Bean
    public PropertySource yamlPropertySourceLoader() throws IOException {

        YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
        return (PropertySource) yamlPropertySourceLoader.load("application.yml",
                new ClassPathResource("application.yml"), "default");
    }

    /**
     * A filter is an object that performs filtering tasks on either the
     * request to a resource (a servlet or static content), or on the response
     * from a resource, or both.
     * <p>
     * <p>Filters perform filtering in the <code>doFilter</code> method.
     * Every Filter has access to a FilterConfig object from which it can obtain
     * its initialization parameters, and a reference to the ServletContext which
     * it can use, for example, to load resources needed for filtering tasks.
     * <p>
     * <p>Filters are configured in the deployment descriptor of a web
     * application.
     * <p>
     * <p>Examples that have been identified for this design are:
     * <ol>
     * <li>Authentication Filters
     * <li>Logging and Auditing Filters
     * <li>Image conversion Filters
     * <li>Data compression Filters
     * <li>Encryption Filters
     * <li>Tokenizing Filters
     * <li>Filters that trigger resource access events
     * <li>XSL/T filters
     * <li>Mime-type chain Filter
     * </ol>
     */
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

}

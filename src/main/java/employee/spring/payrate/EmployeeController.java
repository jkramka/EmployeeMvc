package employee.spring.payrate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
	@Autowired EmployeeDao dao;

	private static final String[ ] countries = { "France", "United States", "Germany", "England" };
	
	@RequestMapping(value = "/form")
	public ModelAndView employee( ){
		ModelAndView modelAndView = new ModelAndView( );
		
		modelAndView.setViewName("employeeForm");
		modelAndView.addObject("employee", new Employee( ));
		modelAndView.addObject("countries", countries);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/result")
	public ModelAndView processEmployee(Employee employee){
		System.out.println("In processEmployee");
		ModelAndView modelAndView = new ModelAndView();
		dao.insertEmployee(employee);
		System.out.println("Value in getName"+ employee.getName());
		modelAndView.setViewName("employeeResult");
		modelAndView.addObject("e", employee);
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewAll")
	public ModelAndView viewAll( ){
		ModelAndView modelAndView = new ModelAndView();
		List<Employee> allEmployees = dao.getAllEmployees();
		modelAndView.setViewName("viewAllEmployees");
		modelAndView.addObject("all", allEmployees);
		return modelAndView;
	}
	
	@Bean
	public EmployeeDao dao(){
		EmployeeDao bean = new EmployeeDao();
		return bean;
	}
	
}

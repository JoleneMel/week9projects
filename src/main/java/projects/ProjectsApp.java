package projects;


import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.projects;
import projects.exception.DbException;
import projects.service.ProjectService;


public class ProjectsApp {
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();
	
	//@formatter:off
	private List<String> operations = List.of(
			"1) Create and populate all tables",
			"2) Add a project"
		);	
		//@formatter:on
	public static void main(String[] args) {
		new ProjectsApp().processUserSelections();	
	}

	private void processUserSelections() {
		boolean done = false;
		
		while(!done) {	
			try {
			int selection= getUsersSelection();
				
			switch(selection) {
			case -1:
				done =exitMenu();
				break;
				
			case 1:
				createProjects();
				break;
				
			case 2:
				addProject();
				break;
				
				default:
					System.out.println("\n"+ selection + "is not vaild. Try again.");
					break;
			}
			
		}catch(Exception e) {
			System.out.println("\nError:"+ e.toString()+ "Try again.");
		}
		}
	}

	private void createProjects() {
		projectService.createAndPoupulateTables();
		System.out.println("\nTables created and pouplated!");
		
	}

	private void addProject() {
	String projectName = getStringInput(" Enter the project name");
	BigDecimal estimatedHours = getDecimalInput( "Enter the estimated hours");
	BigDecimal actualHours= getDecimalInput("Enter the actual Hours");
	Integer difficulty = getIntInput("Enter the project difficulty(1-5)");
	String notes = getStringInput("Enter the project notes");	
	
//	BigDecimal estimatedTime = getDecimalInput(estimatedHours);
//	BigDecimal actualTime = getDecimalInput(actualHours);
	
	projects project = new projects();
	
	project.setProjectName(projectName);
	project.setEstimatedHours(estimatedHours);
	project.setActualHours(actualHours);
	project.setDifficulty(difficulty);
	project.setNotes(notes);
	
	projects dbProject = projectService.addProject(project);
	System.out.println("You have successfully created project:\n"+ dbProject);
	
	}


	//NOTE sometimes for the "feild test error to go away you have to drag and drop, or replace your sql file after rebuilding it 
	//You can do that in Dbeaver by highlighting and running it 
	//or in mysql workbench with the two lines added for create database and use database. 
	
	//The project_id error is due to the non auto_incrementing methods, it needs the auto_increment(which also may need to be reloaded back in like the prior steps)
	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);
		if(Objects.isNull(input)) {
		return null;
	}
		try {
			BigDecimal num = new BigDecimal(input).setScale(2);
			return num;
		}
		catch(NumberFormatException e) {
			throw new DbException(input+ "is not a valid decimal number.");
		}	
		}
	
	private boolean exitMenu() {
		System.out.println("\nExiting the menu. TTFN!");
	return true;
	}

	private int getUsersSelection() {
		printOperations();
		Integer input= getIntInput ("\nEnter and operation number(Press Enter to quit)");
		return Objects.isNull(input)? -1 :input;	
	}

private void printOperations() {
	System.out.println();
	System.out.println("Here's what you can do:");
	operations.forEach(line -> System.out.println("  "+ line));

		
	}
	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
	}
		try {
		return Integer.parseInt(input);
		}
		catch(NumberFormatException e) {
			throw new DbException(input+ " is not a valid number.");
		}
	}
	private String getStringInput(String prompt) {
	System.out.print(prompt+ " :");
	String line = scanner.nextLine();	
	return line.isBlank()? null : line.trim();	
}
	@SuppressWarnings("unused")
	private Double getDoubleInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
	}
		try {
		return Double.parseDouble(input);
		}
		catch(NumberFormatException e) {
			throw new DbException(input+ " is not a valid number.");
		}
	}
}
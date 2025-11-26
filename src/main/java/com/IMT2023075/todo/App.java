package com.IMT2023075.todo;

import java.util.Scanner;

public class App {
    private static final TodoList todo = new TodoList();
    
  public static void main(String[] args) throws Exception {
        System.out.println("ToDo CLI");
        System.out.println("Commands: add <text> | list | remove <id> | exit");
        
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            if (!sc.hasNextLine()){
                break;
            }
            String line = sc.nextLine().trim();
            if (line.isEmpty()){
                continue;
            }
          
            if (line.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for using the app.");
                break;
            }
        
        
            if (line.equalsIgnoreCase("list")) {
                todo.list().forEach(System.out::println);
                continue;
            }
            
            
            if (line.startsWith("add ")) {
                String text = line.substring(4).trim();
                TodoItem it = todo.add(text);
                System.out.println("Added: " + it);
                continue;
            }
            
            
            if (line.startsWith("remove ")) {
                try {
                    int id = Integer.parseInt(line.substring(7).trim());
                    boolean ok = todo.remove(id);
                    System.out.println(ok ? "Removed" : "Not found");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid id");
                }
                continue;
            }
            
            System.out.println("Unknown command");
        }
        
        
        sc.close();
    }
}

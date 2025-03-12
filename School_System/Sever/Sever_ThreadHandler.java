package Sever;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

import Sever_Shop.Shop_Balance;
import Sever_Shop.Shop_Buy;
import Sever_Shop.Shop_Commodity;
import Sever_Shop.Shop_Manager_Data;
import Sever_Shop.Shop_Refund;
import Sever_Shop.Shop_User_Data;
import Sever_StudentInfo.EnrollmentInfoChangeRecords;
import Sever_StudentInfo.Enrollmentinfo;
import Sever_StudentInfo.Student;
import Sever_StudentInfo.StudentInformationManagement;
import Sever_CourseManage.*;
import Sever_Library.*;

import Sever_UserManage.*;

public class Sever_ThreadHandler extends Thread {
	private Socket socket;

	public Sever_ThreadHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			InputStream inputStream = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			OutputStream outputStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outputStream, true);
			String receiveData = reader.readLine();
			while (true) {

				// Read the received data
				if (receiveData.equals(null))
					continue;
				if (receiveData.equals("EXIT"))
				{
					System.out.println(receiveData);
					break;
				}
					
				String jsonStr = receiveData.replaceAll("^[^\\{]*", "");
	            System.out.println("Cleaned JSON Data: " + jsonStr);
				JSONObject jsonObject = new JSONObject(jsonStr);
				int num = jsonObject.getInt("num");
				//商店
				Shop_User_Data li = new Shop_User_Data();
				Shop_Manager_Data ma = new Shop_Manager_Data();
				//学籍管理
				 StudentInformationManagement lol=new StudentInformationManagement();
				 
				  //图书馆
		            Library lib = new Library();
		          //用户登录
		            UserService userService=new UserService();
		          //选课
		            Administrator admin = new Administrator();
					StudentCourse student = new StudentCourse();
				switch (num) {
					case 1: {
						List<Shop_Commodity> commods = li.browseItems();

						JSONArray jsonArray = new JSONArray();

						for (Shop_Commodity commod : commods) {
							JSONObject commodJson = new JSONObject();
							commodJson.put("ID", commod.getID());
							commodJson.put("commodity", commod.getCommodity());
							commodJson.put("money", commod.getMoney());
							commodJson.put("amount", commod.getAmount());
							commodJson.put("category", commod.getCategory());
							jsonArray.put(commodJson);
						}

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 1);
						responseJson.put("commods", jsonArray);

						writer.println(responseJson.toString());
						break;
					}
					case 2: {
						List<Shop_Commodity> commods = li.browseItems();

						JSONArray jsonArray = new JSONArray();

						for (Shop_Commodity commod : commods) {
							JSONObject commodJson = new JSONObject();
							commodJson.put("ID", commod.getID());
							commodJson.put("commodity", commod.getCommodity());
							commodJson.put("money", commod.getMoney());
							commodJson.put("amount", commod.getAmount());
							commodJson.put("category", commod.getCategory());
							commodJson.put("income", commod.getIncome());
							jsonArray.put(commodJson);
						}

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 2);
						responseJson.put("commods", jsonArray);

						writer.println(responseJson.toString());
						break;
					}

					case 3: {
						String categ = jsonObject.getString("category");
						System.out.println(categ);
						List<Shop_Commodity> commods = li.browseItemscategory(categ);

						JSONArray jsonArray = new JSONArray();

						for (Shop_Commodity commod : commods) {
							JSONObject commodJson = new JSONObject();
							commodJson.put("ID", commod.getID());
							commodJson.put("commodity", commod.getCommodity());
							commodJson.put("money", commod.getMoney());
							commodJson.put("category", commod.getCategory());
							commodJson.put("amount", commod.getAmount());
							jsonArray.put(commodJson);
						}

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 3);
						responseJson.put("commods", jsonArray);

						writer.println(responseJson.toString());
						break;
					}
					case 4: {
						String commodi = jsonObject.getString("name");

						List<Shop_Commodity> commods = li.browseItemscommodity(commodi);

						JSONArray jsonArray = new JSONArray();

						for (Shop_Commodity commod : commods) {
							JSONObject commodJson = new JSONObject();
							commodJson.put("ID", commod.getID());
							commodJson.put("commodity", commod.getCommodity());
							commodJson.put("money", commod.getMoney());
							commodJson.put("category", commod.getCategory());
							commodJson.put("amount", commod.getAmount());
							jsonArray.put(commodJson);
						}

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 4);
						responseJson.put("commods", jsonArray);

						writer.println(responseJson.toString());
						break;
					}

					case 5: {

						int commodi = jsonObject.getInt("itemId");
						int amoun = jsonObject.getInt("amount");
						String buye = jsonObject.getString("userId");

						li.buyItems(commodi, amoun, buye);

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 5);
						responseJson.put("opr", "true");

						writer.println(responseJson.toString());
						break;
					}

					case 6: {
						String buyerer = jsonObject.getString("userId");

						List<Shop_Buy> buyes = li.browseItemsbuy(buyerer);

						JSONArray jsonArray = new JSONArray();

						for (Shop_Buy buye : buyes) {
							JSONObject buyJson = new JSONObject();
							buyJson.put("ID", buye.getID());
							buyJson.put("buycommodity", buye.getBuycommodity());
							buyJson.put("buyamount", buye.getBuyamount());
							buyJson.put("buydate", buye.getBuydate());

							buyJson.put("buymoney", buye.getBuymoney());
							buyJson.put("buyer", buye.getBuyer());
							jsonArray.put(buyJson);
						}

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 6);
						responseJson.put("buyes", jsonArray);

						writer.println(responseJson.toString());
						break;
					}

					case 7: {

						int id = jsonObject.getInt("recordId");

						li.refundItems(id);

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 7);
						responseJson.put("opr", "true");

						writer.println(responseJson.toString());
						break;
					}
					case 8: {
						String refunderer = jsonObject.getString("userId");

						List<Shop_Refund> refundes = li.browseItemsrefund(refunderer);

						JSONArray jsonArray = new JSONArray();

						for (Shop_Refund refunde : refundes) {
							JSONObject refundJson = new JSONObject();
							refundJson.put("id", refunde.getId());
							refundJson.put("refundcommodity", refunde.getRefundcommodity());
							refundJson.put("refundamount", refunde.getRefundamount());
							refundJson.put("refunddate", refunde.getRefunddate());

							refundJson.put("refundmoney", refunde.getRefundmoney());
							refundJson.put("refunder", refunde.getRefunder());
							jsonArray.put(refundJson);
						}

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 8);
						responseJson.put("refundes", jsonArray);

						writer.println(responseJson.toString());
						break;
					}

					case 9: {
						System.out.println("收到");
						List<Shop_Buy> buyes = ma.buymanage();

						JSONArray jsonArray = new JSONArray();

						for (Shop_Buy buye : buyes) {
							JSONObject buyJson = new JSONObject();
							buyJson.put("ID", buye.getID());
							buyJson.put("buycommodity", buye.getBuycommodity());
							buyJson.put("buyamount", buye.getBuyamount());
							buyJson.put("buydate", buye.getBuydate());

							buyJson.put("buymoney", buye.getBuymoney());
							buyJson.put("buyer", buye.getBuyer());
							jsonArray.put(buyJson);
						}

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 9);
						responseJson.put("buyes", jsonArray);

						writer.println(responseJson.toString());
						break;
					}

					case 10: {

						List<Shop_Refund> refundes = ma.refundmanage();

						JSONArray jsonArray = new JSONArray();

						for (Shop_Refund refunde : refundes) {
							JSONObject refundJson = new JSONObject();
							refundJson.put("id", refunde.getId());
							refundJson.put("refundcommodity", refunde.getRefundcommodity());
							refundJson.put("refundamount", refunde.getRefundamount());
							refundJson.put("refunddate", refunde.getRefunddate());

							refundJson.put("refundmoney", refunde.getRefundmoney());
							refundJson.put("refunder", refunde.getRefunder());
							jsonArray.put(refundJson);
						}

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 10);
						responseJson.put("refundes", jsonArray);

						writer.println(responseJson.toString());
						break;
					}

					case 11: {
						int amoun = jsonObject.getInt("amount");
						int commodi = jsonObject.getInt("itemId");

						ma.stock(amoun, commodi);

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 11);
						responseJson.put("opr", "true");

						writer.println(responseJson.toString());
						break;
					}

					case 12: {
						String mone = jsonObject.getString("price");
						int amoun = jsonObject.getInt("amount");
						String incom = jsonObject.getString("costprice");
						String categ = jsonObject.getString("category");
						String commodi = jsonObject.getString("name");

						ma.newstock(mone, amoun, incom, categ, commodi);

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 12);
						responseJson.put("opr", "true");

						writer.println(responseJson.toString());
						break;
					}
					case 13: {
					    // 构建响应 JSON 对象
					    JSONObject responseJson = new JSONObject();
					    // 处理 viewAvailableCourses 请求
					    JSONArray coursesArray = new JSONArray();

					    // 调用 viewAvailableCourses 方法并获取结果
					    boolean success = student.viewAvailableCourses(coursesArray);
					    
					    responseJson.put("num", 13);  // 操作标识符
					    responseJson.put("opr", success ? "true" : "false"); // 查询成功或失败
					    if (success) {
					        responseJson.put("courses", coursesArray); // 课程信息数组
					    }

					    // 将响应发送回客户端
					    writer.println(responseJson.toString());
					    break;
					}

					case 14: {
					    // 处理 selectCourse 请求
					    String studentId = jsonObject.optString("student_id", null);
					    String courseId = jsonObject.optString("course_id", null);
					    JSONObject responseJson = new JSONObject();
					    
					    if (studentId == null || courseId == null) {
					        responseJson.put("num", 14);
					        responseJson.put("opr", "false");
					        writer.println(responseJson.toString());
					        break;
					    }

					    boolean success = student.selectCourse(studentId, courseId);
					    
					    responseJson.put("num", 14);  // 操作标识符
					    responseJson.put("opr", success ? "true" : "false");
					    writer.println(responseJson.toString());
					    break;
					}

					case 15: {
					    // 从请求中获取 student_id
					    String studentId = jsonObject.optString("student_id", null);

					    // 创建响应 JSON 对象
					    JSONObject responseJson = new JSONObject();
					    JSONArray enrolledCoursesArray = new JSONArray();

					    if (studentId == null) {
					        responseJson.put("num", 15);
					        responseJson.put("opr", "false");
					        writer.println(responseJson.toString());
					        break;
					    }

					    // 调用 Student 类的 viewEnrolledCourses
					    boolean success = student.viewEnrolledCourses(studentId, enrolledCoursesArray);

					    // 根据方法的返回结果设置响应字段
					    responseJson.put("num", 15); // 设置操作标识符
					    responseJson.put("opr", success ? "true" : "false"); // 查询结果
					    if (success) {
					        responseJson.put("enrolledCourses", enrolledCoursesArray); // 已选课程信息数组
					    }
					    // 将响应发送给客户端
					    writer.println(responseJson.toString());
					    break;
					}

					case 16: {
					    // 处理 dropCourse 请求
					    String studentId = jsonObject.optString("student_id", null);
					    String courseId = jsonObject.optString("course_id", null);
					    JSONObject responseJson = new JSONObject();
					    
					    if (studentId == null || courseId == null) {
					        responseJson.put("num", 16);
					        responseJson.put("opr", "false");
					        writer.println(responseJson.toString());
					        break;
					    }

					    boolean dropSuccess = student.dropCourse(studentId, courseId);

					    responseJson.put("num", 16);  // 操作标识符
					    responseJson.put("opr", dropSuccess ? "true" : "false");
					    writer.println(responseJson.toString());
					    break;
					}

					case 18: {
					    // 处理 scheduleCourse 请求
						String courseId = jsonObject.optString("course_id", null); 
					    String courseName = jsonObject.optString("course_name", null);
					    String courseTime = jsonObject.optString("course_time", null);
					    String courseLocation = jsonObject.optString("course_location", null);
					    int courseCapacity = jsonObject.optInt("course_capacity", -1);
					    String instructorId = jsonObject.optString("instructor_id", null);

					    JSONObject responseJson = new JSONObject();
					    
					    if (courseId==null|| courseName== null || courseTime == null || courseLocation == null || courseCapacity <= 0 || instructorId == null) {
					        responseJson.put("num", 18);
					        responseJson.put("opr", "false");
					        writer.println(responseJson.toString());
					        break;
					    }

					    boolean success = admin.scheduleCourse(courseId,courseName, courseTime, courseLocation, courseCapacity, instructorId);

					    responseJson.put("num", 18);
					    responseJson.put("opr", success ? "true" : "false");
					    writer.println(responseJson.toString());
					    break;
					}

					case 19: {
					    // 处理 deleteCourse 请求
					    String courseId = jsonObject.optString("course_id", null);

					    JSONObject responseJson = new JSONObject();
					    
					    if (courseId == null) {
					        responseJson.put("num", 19);
					        responseJson.put("opr", "false");
					        writer.println(responseJson.toString());
					        break;
					    }

					    boolean success = admin.deleteCourse(courseId);

					    responseJson.put("num", 19);
					    responseJson.put("opr", success ? "true" : "false");
					    writer.println(responseJson.toString());
					    break;
					}

					case 20: {
					    // 处理 viewTeacherCourses 请求
					    String teacherId = jsonObject.optString("teacherId", null);

					    JSONObject responseJson = new JSONObject();
					    JSONArray coursesArray = new JSONArray();
					    
					    if (teacherId == null) {
					        responseJson.put("num", 20);
					        responseJson.put("opr", "false");
					        writer.println(responseJson.toString());
					        break;
					    }

					    Teacher teacher = new Teacher();
					    boolean success = teacher.viewTeacherCourses(teacherId, coursesArray);

					    responseJson.put("num", 20); // 设置操作标识符
					    responseJson.put("opr", success ? "true" : "false"); // 查询成功或失败
					    if (success) {
					        responseJson.put("courses", coursesArray); // 课程信息数组
					    }

					    // 将响应发送给客户端
					    writer.println(responseJson.toString());
					    break;
					}

					
					case 21: {
					    // 处理 exportStudentList 请求
					    String teacherId = jsonObject.optString("teacher_id", null); // 确保键名与请求一致
					    int courseId = jsonObject.optInt("courseId", 0); // 确保键名与请求一致

					    JSONObject responseJson = new JSONObject();
					    JSONArray studentArray = new JSONArray();
					    
					    if (teacherId == null || courseId == 0) {
					        responseJson.put("num", 21);
					        responseJson.put("opr", "false");
					        writer.println(responseJson.toString());
					        break;
					    }

					    Teacher teacher = new Teacher(); // 修改为无参构造函数
					    boolean success = teacher.exportStudentList(teacherId, courseId, studentArray);

					    responseJson.put("num", 21); // 设置操作标识符
					    responseJson.put("opr", success ? "true" : "false"); // 导出成功或失败
					    responseJson.put("students", studentArray); 
					    writer.println(responseJson.toString());
					    break;
					}
			            case 22: // Register
			            { String registerUserId = jsonObject.getString("id");
			                String registerUserPassword = jsonObject.getString("psw");
			                String registerIdentity = jsonObject.getString("type");
			                System.out.println(registerUserId);
			                System.out.println(registerUserPassword);
			                System.out.println(registerIdentity);
			                
			                boolean registerResult = userService.register(registerUserId, registerUserPassword, registerIdentity);
			                JSONObject registerResponse = new JSONObject();
			                registerResponse.put("num", num);
			                registerResponse.put("opr", registerResult ? "true" : "false");
			                System.out.println(registerResponse.toString());
			                writer.println(registerResponse.toString());
			                break;
			            }
			            case 23: // Login
			            { String loginUserId = jsonObject.getString("id");
			                String loginUserPassword = jsonObject.getString("psw");
			                String loginIdentity = jsonObject.getString("type");
			                boolean loginResult = "true".equals(userService.login(loginUserId, loginUserPassword, loginIdentity));
			                JSONObject loginResponse = new JSONObject();
			                loginResponse.put("num", num);
			                loginResponse.put("opr", loginResult ? "true" : "false");
			                writer.println(loginResponse.toString());
			                break;
			            }
			               case 24:
			               {
			            	     List<Book> books = lib.getBooks();
			            	     
			            	 
			            	     JSONArray jsonArray = new JSONArray();
			            	     
			  
			            	     for (Book book : books) {
			            	         JSONObject bookJson = new JSONObject();
			            	         bookJson.put("bookid", book.getBookID());
			            	         bookJson.put("category", book.getCategory());
			            	         bookJson.put("bookname", book.getBookname());
			            	         bookJson.put("author", book.getAuthor());
			            	         bookJson.put("publisher", book.getPublisher());
			            	         bookJson.put("remain", book.getRemain());
			            	         bookJson.put("total", book.getTotal());
			            	         bookJson.put("price", book.getPrice());
			            	         jsonArray.put(bookJson);
			            	     }
			            	     
			            
			            	     JSONObject responseJson = new JSONObject();
			            	     responseJson.put("num", 24); 
			            	     responseJson.put("books", jsonArray); 
			            	   
			            	     System.out.println("Data: " + responseJson);
			            	     writer.println(responseJson.toString());
			            	     
			            	     break;
			            	 }
			            
			               case 25:
			               {
			            	   String bookname = jsonObject.getString("bookname");
			            	   
			            	   List<Book> books = lib.getBook(bookname);
			            	
			          	     JSONArray jsonArray = new JSONArray();
			          	     
			          	     
			          	     for (Book book : books) {
			          	         JSONObject bookJson = new JSONObject();
			          	         bookJson.put("bookid", book.getBookID());
			          	         bookJson.put("category", book.getCategory());
			          	         bookJson.put("bookname", book.getBookname());
			          	         bookJson.put("author", book.getAuthor());
			          	         bookJson.put("publisher", book.getPublisher());
			          	         bookJson.put("remain", book.getRemain());
			          	         bookJson.put("total", book.getTotal());
			          	         bookJson.put("price", book.getPrice());
			          	         jsonArray.put(bookJson);
			          	     }
			          	     
			          	     
			          	     JSONObject responseJson = new JSONObject();
			          	     responseJson.put("num", 25); 
			          	     responseJson.put("books", jsonArray); 
			          	     
			          	     
			          	     writer.println(responseJson.toString());
			          	     break;
			               }
			            
			                                 
			               case 26:
			                              {
			                               	
			                                   String bookid = jsonObject.getString("bookid");
			                                   String category = jsonObject.getString("category");
			                                   String bookname = jsonObject.getString("bookname");
			                                   String author = jsonObject.getString("author");
			                                   String publisher = jsonObject.getString("publisher");
			                                   int remain = jsonObject.getInt("remain");
			                                   int total = jsonObject.getInt("total");
			                                   int price = jsonObject.getInt("price");

			                                  
			                                   
			                                   Book b = new Book(bookid, category, bookname, author, publisher, remain, total, price);
			                                   
			                                   if(lib.addBook(b))
			                                   {
			                                   	JSONObject responseJson = new JSONObject();
			                               	     responseJson.put("num", 26); 
			                               	     responseJson.put("opr", "true");
			                               	     writer.println(responseJson.toString());
			                                   }
			                                   else
			                                   {
			                                   	JSONObject responseJson = new JSONObject();
			                              	     responseJson.put("num", 26); 
			                              	     responseJson.put("opr", "false");
			                              	     writer.println(responseJson.toString());
			                                   }
			                                            
			                            	     
			                            	     break;
			                              }

			              
			               case 27:
			                              {
			                           	   String bookid = jsonObject.getString("bookid");
			                           	   
			                           	   if(lib.search(bookid))
			                           	   {
			                           		   if(lib.delBook(bookid))
			                           		   {
			                           			   JSONObject responseJson = new JSONObject();
			                                    	   responseJson.put("num", 27); 
			                                    	   responseJson.put("opr", "true"); 
			                                    	  writer.println(responseJson.toString());
			                           		   }
			                           		   else
			                           		   {
			                           			   JSONObject responseJson = new JSONObject();
			                                    	   responseJson.put("num", 27); 
			                                    	   responseJson.put("opr", "false"); 
			                                    	  writer.println(responseJson.toString());
			                           		   }
			                           	   }
			                           	   else 
			                           	   {
			                           		   JSONObject responseJson = new JSONObject();
			                                	   responseJson.put("num", 27); 
			                                	   responseJson.put("opr", "false"); 
			                                	  writer.println(responseJson.toString());
			                           	   }
			                           	   
			                          	   
			                          	     break;
			                              }
			                              
			               case 28:
			               {
			            	     List<Borrow> borrows = lib.getBorrow();
			             	     JSONArray jsonArray = new JSONArray();
			             	     
			             	     for (Borrow record : borrows) {
			             	         JSONObject bookJson = new JSONObject();
			             	         bookJson.put("bookid", record.getBookID());
			             	         bookJson.put("bookname", record.getBookname());
			             	         bookJson.put("author", record.getAuthor());
			             	         bookJson.put("btime", record.getBtime());
			             	         bookJson.put("ltime", record.getLtime());
			             	         bookJson.put("renew", record.getRenew());
			             	         jsonArray.put(bookJson);
			             	     }
			             	     
			             	     JSONObject responseJson = new JSONObject();
			             	     responseJson.put("num", 28); 
			             	     responseJson.put("books", jsonArray); 
			             	     
			             	     writer.println(responseJson.toString());
			             	     break;
			               }
			               
			               case 29:
			               {
			            	 List<History> historys = lib.getHistory();
			           	     JSONArray jsonArray = new JSONArray();
			           	     
			           	     for (History record : historys) {
			           	         JSONObject bookJson = new JSONObject();
			           	         bookJson.put("id", record.getID());
			           	         bookJson.put("bookid", record.getBookID());
			           	         bookJson.put("bookname", record.getBookname());
			           	         bookJson.put("author", record.getAuthor());
			           	         bookJson.put("btime", record.getBtime());
			           	         bookJson.put("ltime", record.getLtime());
			           	         
			           	         jsonArray.put(bookJson);
			           	     }
			           	     
			           	     JSONObject responseJson = new JSONObject();
			           	     responseJson.put("num", 29); 
			           	     responseJson.put("books", jsonArray); 
			           	     
			           	     writer.println(responseJson.toString());
			           	     break;
			               }
			               
			               case 30:
                           {
                             String bookid = jsonObject.getString("bookid");
                             String id = jsonObject.getString("id");
                        	   
                        	 if(lib.renew(bookid,id))
                        	 {
                        		 JSONObject responseJson = new JSONObject();
                           	     responseJson.put("num", 30); 
                           	     responseJson.put("opr", "true");
                           	     writer.println(responseJson.toString());
                        	 }
                        	 else
                        	 {
                        		 JSONObject responseJson = new JSONObject();
                           	     responseJson.put("num", 30); 
                           	     responseJson.put("opr", "false");
                           	     writer.println(responseJson.toString());
                        	 }
                        	
                       	     
                       	     break;
                           }

			               
			               case 31:
			                              {
			                           	   String bookid = jsonObject.getString("bookid");
			                           	   String id = jsonObject.getString("id");
			                           	   
			                             	   if(lib.borrow(bookid,id))
			                             	   {
			                             		 JSONObject responseJson = new JSONObject();
			                            	     responseJson.put("num", 31); 
			                            	     responseJson.put("opr", "true"); 
			                            	     
			                            	     
			                            	     writer.println(responseJson.toString());
			                             	   }
			                             	   else
			                             	   {
			                             		 JSONObject responseJson = new JSONObject();
			                            	     responseJson.put("num", 31); 
			                            	     responseJson.put("opr", "false"); 
			                            	     
			                            	     
			                            	     writer.println(responseJson.toString());
			                             	   }
			                             	
			                            	     
			                            	     break;
			                              }

			        
			               case 32:
			                              {
			                           	   String bookid = jsonObject.getString("bookid");

			                           	   
			                             	   if(lib.returnBook(bookid))
			                             	   {
			                             		 JSONObject responseJson = new JSONObject();
			                            	     responseJson.put("num", 32); 
			                            	     responseJson.put("opr", "true"); 
			                            	     
			                            	     
			                            	     writer.println(responseJson.toString());
			                             	   }
			                             	   else
			                             	   {
			                             		 JSONObject responseJson = new JSONObject();
			                            	     responseJson.put("num", 32); 
			                            	     responseJson.put("opr", "false"); 
			                            	     
			                            	     
			                            	     writer.println(responseJson.toString());
			                             	   }
			                             	
			                            	     
			                            	     break;
			                              }

					case 33:
		               {String id = jsonObject.getString("id");
		                Student stu = lol.viewStudentBasicInformation(id);
		                
		                if (stu != null) {
		                    JSONObject stuJson = new JSONObject();
		                    stuJson.put("num", 33);
		                    stuJson.put("id", stu.getStudentIdNum());
		                    
		                    // 对各个字段进行 null 检查，避免插入 null 值
		                    stuJson.put("psw", stu.getStudentPassword() != null ? stu.getStudentPassword() : "");
		                    stuJson.put("name", stu.getStudentName() != null ? stu.getStudentName() : "");
		                    stuJson.put("gender", stu.getStudentGender() != null ? stu.getStudentGender() : "");
		                    stuJson.put("birth", stu.getStudentBirthday() != null ? stu.getStudentBirthday() : "");
		                    stuJson.put("idCard", stu.getStudentIdCard() != null ? stu.getStudentIdCard() : "");
		                    stuJson.put("nationality", stu.getStudentNationality() != null ? stu.getStudentNationality() : "");
		                    stuJson.put("homeAd", stu.getStudentHomeAddress() != null ? stu.getStudentHomeAddress() : "");
		                    stuJson.put("enrollmentdate", stu.getStudentEnrollmentdate() != null ? stu.getStudentEnrollmentdate() : "");
		                    stuJson.put("tele", stu.getStudentTelephoneNum() != null ? stu.getStudentTelephoneNum() : "");
		                    stuJson.put("email", stu.getStudentEmail() != null ? stu.getStudentEmail() : "");
		                    stuJson.put("grade", stu.getStudentSchoolGrade() != null ? stu.getStudentSchoolGrade() : "");
		                    stuJson.put("claId", stu.getStudentClassIdNum() != null ? stu.getStudentClassIdNum() : "");
		                    stuJson.put("claName", stu.getStudentClassName() != null ? stu.getStudentClassName() : "");
		                    
		                    writer.println(stuJson.toString());
		                } else {
		                    System.out.println("未找到该学生信息");
		                }
		           	     break;
		               }
		               
		              
		               case 34:
		               {
		                   String id = jsonObject.getString("id");
		                   Enrollmentinfo ero = lol.viewStudentEnrollmentInformation(id);
		                   
		                   if (ero != null) {
		                       JSONObject eroJson = new JSONObject();
		                       eroJson.put("num", 34);
		                       eroJson.put("id", ero.getStudentIdNum() != null ? ero.getStudentIdNum() : "");
		                       eroJson.put("enrolltype", ero.getEnrollmentInfoType() != null ? ero.getEnrollmentInfoType() : "");
		                       eroJson.put("enrollstatus", ero.getEnrollmentInfoStatus() != null ? ero.getEnrollmentInfoStatus() : "");
		                       eroJson.put("enrollyear", ero.getEnrollmentInfoYearDegree() != null ? ero.getEnrollmentInfoYearDegree() : "");
		                       
		                       writer.println(eroJson.toString());
		                   } else {
		                       System.out.println("未找到该学生的学籍信息");
		                   }
		                   break;
		               }
		               
		               
		               case 35:
		               {
		            	   String id=jsonObject.getString("id");
		            	   String psw=jsonObject.getString("psw");
		            	   String tele=jsonObject.getString("tele");
		            	   String homeAd=jsonObject.getString("homeAd");
		            	   String email=jsonObject.getString("email");
		            	   
		            	   Student stu=new Student(id,psw,tele,homeAd,email);
		            	   lol.updateStudentBasicInformation_student(id, stu);
		            	   
		            	   JSONObject responseJson = new JSONObject();
		           	       responseJson.put("num", 35); 
		           	       responseJson.put("opr", "true"); 
		           	     
		           	    
		           	       writer.println(responseJson.toString());
		           	       break;

		               }
		               
		               case 36:
		               {
		            	   String id=jsonObject.getString("id");
		            	  
		            	   String type=jsonObject.getString("type");
		            	   String reason=jsonObject.getString("reason");
		            	   String date=jsonObject.getString("date");
		            	   
		            	   
		            	   
		            	   
		            	   lol.studentRequestEnrollmentInfoChange(id, type, date,reason);
		            	   
		            	   JSONObject responseJson = new JSONObject();
		           	       responseJson.put("num", 36); 
		           	       responseJson.put("opr", "true"); 
		            	   
		           	       writer.println(responseJson.toString());
		        	       break;
		               }
		               
		               case 37:
		               {
		            	   String id=jsonObject.getString("id");
		            	   
		            	   List<EnrollmentInfoChangeRecords> enrollmentInfoChangeRecords=lol.viewStudentEnrollmentInfomationChangeRecords(id);
		            	   JSONArray jsonArray = new JSONArray();
		            	   
		            	   for(EnrollmentInfoChangeRecords enrollmentInfoChangeRecord:enrollmentInfoChangeRecords)
		            	   {
		            		   JSONObject Json = new JSONObject();
		            		   Json.put("recordId",enrollmentInfoChangeRecord.getRecordIdNum());
		            		   Json.put("studentId",enrollmentInfoChangeRecord.getStudentIdNum());
		            		   Json.put("type",enrollmentInfoChangeRecord.getEnrollmentInfoChangeType());
		            		   Json.put("reason",enrollmentInfoChangeRecord.getEnrollmentInfoChangeReason());
		            		   Json.put("state",enrollmentInfoChangeRecord.getEnrollmentInfoChangeState());
		            		   Json.put("time",enrollmentInfoChangeRecord.getEnrollmentInfoChangeTime());
		            		   
		            		   
		                 	   jsonArray.put(Json);
		            		   
		            	   }
		            	   
		            	   JSONObject responseJson = new JSONObject();
		            	     responseJson.put("num", 37); 
		            	     responseJson.put("record", jsonArray); 
		            	     
		            	     
		            	     writer.println(responseJson.toString());
		            	     break;
		               }
		            
		               case 38:
		               {
		            	   String id=jsonObject.getString("id");
		            	   String psw=jsonObject.getString("psw");
		            	   String name=jsonObject.getString("name");
		            	   String gender=jsonObject.getString("gender");
		            	   String birth=jsonObject.getString("birth");
		            	   
		            	   
		            	   
		            	   String idCard=jsonObject.getString("idCard");
		            	   String nationality=jsonObject.getString("nationality");
		            	   
		            	   String enrollmentdate=jsonObject.getString("enrollDate");
		            	   
		            	   
		            	   
		            	   String tele=jsonObject.getString("tele");
		            	   String homeAd=jsonObject.getString("homeAd");
		            	   String email=jsonObject.getString("email");
		            	   String grade=jsonObject.getString("grade");
		            	   String claId=jsonObject.getString("classId");
		            	   String claName=jsonObject.getString("className");
		            	   
		            	   Student stu=new Student(id,psw,name,gender,birth,nationality,homeAd,idCard,enrollmentdate,tele,email,grade,claId,claName);
		            	   lol.updateStudentBasicInformation_admin(id, stu);
		            	   
		            	   JSONObject responseJson = new JSONObject();
		           	       responseJson.put("num", 38); 
		           	       responseJson.put("opr", "true"); 
		           	     
		           	    
		           	       writer.println(responseJson.toString());
		           	       break;
		   
		               }
		            
		               case 39:
		               {
		            	   String id=jsonObject.getString("id");
		            	   String type=jsonObject.getString("type");
		            	   String status=jsonObject.getString("status");
		            	   String enrollyear=jsonObject.getString("yearDegree");
		            	   
		            	   Enrollmentinfo ero=new Enrollmentinfo(id,type,status,enrollyear);
		            	   lol.updateStudentEnrollmentInformation(id, ero);
		            	   
		            	   JSONObject responseJson = new JSONObject();
		           	       responseJson.put("num", 39); 
		           	       responseJson.put("opr", "true"); 
		           	     
		           	    
		           	       writer.println(responseJson.toString());
		           	       break;
		            	   
		            	   
		               }
		            
		               case 40:
		               {
		            	   String id=jsonObject.getString("id");
		            	   lol.processStudentEnrollmentInfoChangeRequest(id);
		            	   
		            	   JSONObject responseJson = new JSONObject();
		           	       responseJson.put("num", 40); 
		           	       responseJson.put("opr", "true"); 
		           	     
		           	    
		           	       writer.println(responseJson.toString());
		           	       break;
		               }
		               
		               case 41:
		               {
		            	   List<EnrollmentInfoChangeRecords> enrollmentInfoChangeRecords=lol.getEnrollmentInfoChangePendingRequests();
		            	   JSONArray jsonArray = new JSONArray();
		            	   
		            	   for(EnrollmentInfoChangeRecords enrollmentInfoChangeRecord:enrollmentInfoChangeRecords)
		            	   {
		            		   JSONObject Json = new JSONObject();
		            		   Json.put("recordId",enrollmentInfoChangeRecord.getRecordIdNum());
		            		   Json.put("studentId",enrollmentInfoChangeRecord.getStudentIdNum());
		            		   Json.put("type",enrollmentInfoChangeRecord.getEnrollmentInfoChangeType());
		            		   Json.put("reason",enrollmentInfoChangeRecord.getEnrollmentInfoChangeReason());
		            		   Json.put("state",enrollmentInfoChangeRecord.getEnrollmentInfoChangeState());
		            		   Json.put("time",enrollmentInfoChangeRecord.getEnrollmentInfoChangeTime());
		            		   
		            		   
		                 	   jsonArray.put(Json);
		            		   
		            	   }
		            	   
		            	   JSONObject responseJson = new JSONObject();
		            	     responseJson.put("num", 41); 
		            	     responseJson.put("requests", jsonArray); 
		            	     
		            	     
		            	     writer.println(responseJson.toString());
		            	     break;
		            	   
		            	   
		               }
		            
		               case 42:
		               {
		            	   List<EnrollmentInfoChangeRecords> enrollmentInfoChangeRecords=lol.getEnrollmentInfoChangeRequests();
		                   JSONArray jsonArray = new JSONArray();
		            	   
		            	   for(EnrollmentInfoChangeRecords enrollmentInfoChangeRecord:enrollmentInfoChangeRecords)
		            	   {
		            		   JSONObject Json = new JSONObject();
		            		   Json.put("recordId",enrollmentInfoChangeRecord.getRecordIdNum());
		            		   Json.put("studentId",enrollmentInfoChangeRecord.getStudentIdNum());
		            		   Json.put("type",enrollmentInfoChangeRecord.getEnrollmentInfoChangeType());
		            		   Json.put("reason",enrollmentInfoChangeRecord.getEnrollmentInfoChangeReason());
		            		   Json.put("state",enrollmentInfoChangeRecord.getEnrollmentInfoChangeState());
		            		   Json.put("time",enrollmentInfoChangeRecord.getEnrollmentInfoChangeTime());
		            		   
		            		   
		                 	   jsonArray.put(Json);
		            		   
		            	   }
		            	   
		            	   JSONObject responseJson = new JSONObject();
		            	     responseJson.put("num", 41); 
		            	     responseJson.put("requests", jsonArray); 
		            	     
		            	     
		            	     writer.println(responseJson.toString());
		            	     break;
		            	   
		            	   
		               }
		               
		               case 44:
		               {
		            	   String id=jsonObject.getString("id");
		            	   lol.addStudentId(id);
		            	   
		            	   JSONObject responseJson = new JSONObject();
		           	       responseJson.put("num", 44); 
		           	       responseJson.put("opr", "true"); 
		           	     
		           	    
		           	       writer.println(responseJson.toString());
		           	       break;
		            	   
		               }
		               
		               case 45:
		               {
		            	   String id=jsonObject.getString("id");
		            	   lol.deleteStudent(id);
		            	   
		            	   JSONObject responseJson = new JSONObject();
		           	       responseJson.put("num", 44); 
		           	       responseJson.put("opr", "true"); 
		           	     
		           	    
		           	       writer.println(responseJson.toString());
		           	       break;
		               }
		            
					case 55: {
						String userID = jsonObject.getString("userId");
						List<Shop_Balance> balances = li.checkbalance(userID);
						JSONArray jsonArray = new JSONArray();

						String Balance="0.00";
						for (Shop_Balance balance : balances) {
							Balance = balance.getUserbalance();
						}

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 55);
						responseJson.put("balance", Balance);

						writer.println(responseJson.toString());
						break;

					}
					case 56: {
		
						String userID = jsonObject.getString("userId");
						String money = jsonObject.getString("money");
						li.addBalance(userID, money);

						JSONObject responseJson = new JSONObject();
						responseJson.put("num", 56);
						responseJson.put("opr", true);

						writer.println(responseJson.toString());
						break;
					}
					 case 57:
		               {
		            	   String id = jsonObject.getString("id");
		            	   
		            	   List<Borrow> borrows = lib.getSearchBorrow(id);
		           	       JSONArray jsonArray = new JSONArray();
		           	     
		           	     for (Borrow record : borrows) {
		           	         JSONObject bookJson = new JSONObject();
		           	         bookJson.put("id", record.getStudentID());
		           	         bookJson.put("bookid", record.getBookID());
		           	         bookJson.put("bookname", record.getBookname());
		           	         bookJson.put("author", record.getAuthor());
		           	         bookJson.put("btime", record.getBtime());
		           	         bookJson.put("ltime", record.getLtime());
		           	         bookJson.put("renew", record.getRenew());
		           	         jsonArray.put(bookJson);
		           	     }
		           	     
		           	     JSONObject responseJson = new JSONObject();
		           	     responseJson.put("num", 57); 
		           	     responseJson.put("books", jsonArray); 
		           	     
		           	     writer.println(responseJson.toString());
		           	     break;
		               }
		               
		               case 58:
		               {
		            	   
		            	   String id = jsonObject.getString("id");
		            	   
		            	   List<History> historys = lib.getSearchHistory(id);
		             	     JSONArray jsonArray = new JSONArray();
		             	     
		             	     for (History record : historys) {
		             	         JSONObject bookJson = new JSONObject();
		             	         bookJson.put("id", record.getID());
		             	         bookJson.put("studentid", record.getStudentID());
		             	         bookJson.put("bookid", record.getBookID());
		             	         bookJson.put("bookname", record.getBookname());
		             	         bookJson.put("author", record.getAuthor());
		             	         bookJson.put("btime", record.getBtime());
		             	         bookJson.put("ltime", record.getLtime());
		             	         
		             	         jsonArray.put(bookJson);
		             	     }
		             	     
		             	     JSONObject responseJson = new JSONObject();
		             	     responseJson.put("num", 58); 
		             	     responseJson.put("books", jsonArray); 
		             	     
		             	     writer.println(responseJson.toString());
		             	     break;
		               }
				}

				// Close resources
				// reader.close();
				// writer.close();
				// socket.close();
				receiveData = reader.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}

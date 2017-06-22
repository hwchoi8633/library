package 최현욱_30126;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	static String filepath = "C:/test/30126_최현욱.txt";
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		System.out.println("도서 관리 프로그램입니다.");
		
		int choice = -1;
		
		while(true) {
			System.out.println("\n[1]:전체 도서 리스트 [2]:도서 검색 [3]:신규 도서 추가 [4]:노후 도서 폐기 [5]:도서 대여 [6]:도서 반납 [0]:프로그램 종료");
			System.out.print("메뉴를 선택하세요 : ");
			choice = scan.nextInt();
			scan.nextLine();
			
			switch(choice) {
			case 1:
				Print_Book();
				break;
			case 2:
				Search_Book();
				break;
			case 3:
				Add_Book();
				break;
			case 4:
				Del_Book();
				break;
			case 5:
				Rental_Book();
				break;
			case 6:
				Return_Book();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
				break;
			default:
				System.err.println("다시 입력해주세요.");
			}
		}
	}
	
	public static boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static void Add_Book() throws IOException {
		Book book = new Book();
		
		System.out.print("\n책 이름 : ");
		book.setName(scan.nextLine());
		System.out.print("저자 : ");
		book.setAuthor(scan.nextLine());
		System.out.print("출판사 : ");
		book.setPublisher(scan.nextLine());
		while(true) {
			System.out.print("가격 : ");
			book.setCost(scan.nextLine());
			String checkstr = book.getCost();
			if(isStringDouble(checkstr))
				break;
			else
				System.out.println("숫자만 입력하세요.");
		}
		
		
		System.out.println("\n입력한 정보를 확인해주세요.");
		System.out.println("책 이름 : " + book.getName());
		System.out.println("저자 : " + book.getAuthor());
		System.out.println("출판사 : " + book.getPublisher());
		System.out.println("가격 : " + book.getCost());
		
		System.out.print("\n입력한 정보가 맞습니까? [y/n] : ");
		String yn = scan.next();
		scan.nextLine();
		
		switch(yn) {
		case "y":
			BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
			bw.write(book.getName() + "/" + book.getAuthor() + "/" + book.getPublisher() + "/" + book.getCost() + "/대여가능/X");
			bw.newLine();
			bw.close();
			System.out.println(book.getName() + " 도서를 추가했습니다.");
			break;
		case "n":
			System.out.println("\n다시 입력해주세요.");
			Add_Book();
			break;
		}
	}
	
	public static void Print_Book() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String str = "";
		String[] arr = null;
		int count = 0;
		
		System.out.println("\nNo\t책 이름\t저자\t출판사\t가격\t대여여부\t대여자명\n");
		while((str = br.readLine()) != null) {
			arr = str.split("/");
			count++;
			System.out.println(count + "\t" + arr[0] + "\t" + arr[1] + "\t" + arr[2] + "\t" + arr[3] + "\t" + arr[4] + "\t" + arr[5]);
		}
		System.out.println("총 " + count + " 건이 검색되었습니다.");
		br.close();
	}
	
	public static void Search_Book() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String str = "", keyword;
		String[] arr = null;
		int count = 0, choice = -1;
		
		System.out.println("[1]:제목 [2]:저자 [3]:통합");
		System.out.print("검색 방법을 선택하세요 : ");
		choice = scan.nextInt();
		scan.nextLine();
		
		switch(choice) {
		case 1:
			System.out.print("\n검색할 도서명을 입력해주세요 : ");
			keyword = scan.nextLine();
			
			System.out.println("\nNo\t책 이름\t저자\t출판사\t가격\t대여여부\t대여자명\n");
			while((str = br.readLine()) != null) {
				arr = str.split("/");
				if(arr[0].contains(keyword)) {
					count++;
					System.out.println(count + "\t" + arr[0] + "\t" + arr[1] + "\t" + arr[2] + "\t" + arr[3] + "\t" + arr[4] + "\t" + arr[5]);
				}
			}
			System.out.println("총 " + count + " 건이 검색되었습니다.");
			br.close();
			break;
		case 2:
			System.out.print("\n검색할 저자명을 입력해주세요 : ");
			keyword = scan.nextLine();
			
			System.out.println("\nNo\t책 이름\t저자\t출판사\t가격\t대여여부\t대여자명\n");
			while((str = br.readLine()) != null) {
				arr = str.split("/");
				if(arr[1].contains(keyword)) {
					count++;
					System.out.println(count + "\t" + arr[0] + "\t" + arr[1] + "\t" + arr[2] + "\t" + arr[3] + "\t" + arr[4] + "\t" + arr[5]);
				}
			}
			System.out.println("총 " + count + " 건이 검색되었습니다.");
			br.close();
			break;
		case 3:
			System.out.print("\n검색할 키워드(제목/저자)를 입력해주세요 : ");
			keyword = scan.nextLine();
			
			System.out.println("\nNo\t책 이름\t저자\t출판사\t가격\t대여여부\t대여자명\n");
			while((str = br.readLine()) != null) {
				arr = str.split("/");
				if(arr[0].contains(keyword) || arr[1].contains(keyword)) {
					count++;
					System.out.println(count + "\t" + arr[0] + "\t" + arr[1] + "\t" + arr[2] + "\t" + arr[3] + "\t" + arr[4] + "\t" + arr[5]);
				}
			}
			System.out.println("총 " + count + " 건이 검색되었습니다.");
			br.close();
			break;
		default:
			System.out.println("다시 입력해주세요.");
		}
	}
	
	public static void Del_Book() throws IOException {
		String tmpfilepath = filepath + ".tmp";
		String str = "";
		String[] arr = null;
		int count = 0;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(tmpfilepath, true));
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		
		Print_Book();
		
		System.out.print("\n삭제할 도서번호를 입력해주세요 : ");
		int delnum = scan.nextInt();
		scan.nextLine();
		
		while((str = br.readLine()) != null) {
			count++;
			arr = str.split("/");
			if(count != delnum) {
				bw.write(str);
				bw.newLine();
			}
			else if(count == delnum) {
				if(arr[4].matches("대여중")) {
					System.out.println("대여중인 도서는 폐기할수 없습니다.");
					bw.write(str);
					bw.newLine();
				}
				else if(arr[4].matches("대여가능")) {
					System.out.println("삭제 완료");
				}
			}
		}
		bw.close();
		br.close();
		
		Path file = Paths.get(tmpfilepath);
		Path filetomove = Paths.get(filepath);
		Files.delete(filetomove);
		Files.move(file, filetomove);
	}
	
	public static void Rental_Book() throws IOException {
		String tmpfilepath = filepath + ".tmp";
		String str = "";
		String[] arr = null;
		int count = 0;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(tmpfilepath, true));
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		
		Print_Book();
		
		System.out.print("\n대여할 도서번호를 입력해주세요 : ");
		int delnum = scan.nextInt();
		scan.nextLine();
		
		while((str = br.readLine()) != null) {
			count++;
			arr = str.split("/");
			if(count != delnum) {
				bw.write(str);
				bw.newLine();
			}
			else {
				if(arr[4].matches("대여중")) {
					System.out.println("이미 대여중인 도서입니다.");
					bw.write(str);
					bw.newLine();
				}
				else if(arr[4].matches("대여가능")) {
					arr[4] = "대여중";
					System.out.print("대여자 성함을 입력해주세요 : ");
					String rname = scan.nextLine();
					arr[5] = rname;
					bw.write(arr[0] + "/" + arr[1] + "/" + arr[2] + "/" + arr[3] + "/" + arr[4] + "/" + arr[5]);
					bw.newLine();
				}
			}
			
		}
		bw.close();
		br.close();
		
		Path file = Paths.get(tmpfilepath);
		Path filetomove = Paths.get(filepath);
		Files.delete(filetomove);
		Files.move(file, filetomove);
	}
	
	public static void Return_Book() throws IOException {
		String tmpfilepath = filepath + ".tmp";
		String str = "";
		String[] arr = null;
		int count = 0;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(tmpfilepath, true));
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		
		Print_Book();
		
		System.out.print("\n반납할 도서번호를 입력해주세요 : ");
		int delnum = scan.nextInt();
		scan.nextLine();
		
		while((str = br.readLine()) != null) {
			count++;
			arr = str.split("/");
			if(count != delnum) {
				bw.write(str);
				bw.newLine();
			}
			else {
				if(arr[4].matches("대여가능")) {
					System.out.println("대여중인 도서가 아닙니다.");
					bw.write(str);
					bw.newLine();
				}
				else if(arr[4].matches("대여중")) {
					arr[4] = "대여가능";
					arr[5] = "X";
					bw.write(arr[0] + "/" + arr[1] + "/" + arr[2] + "/" + arr[3] + "/" + arr[4] + "/" + arr[5]);
					bw.newLine();
				}
			}
		}
		bw.close();
		br.close();
		
		Path file = Paths.get(tmpfilepath);
		Path filetomove = Paths.get(filepath);
		Files.delete(filetomove);
		Files.move(file, filetomove);
	}
}

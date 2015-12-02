import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import vn.hus.nlp.tagger.VietnameseMaxentTagger;

public class QA {
	public static boolean FindTypeQuestion(String FileName, String question) throws FileNotFoundException{
		FileInputStream fis = new FileInputStream(FileName);
		 Scanner scanner = new Scanner(fis);
		 int questionLength = question.length();
		 String findIt;
		 int  finItLength;
		 while(scanner.hasNextLine()){
	            findIt = scanner.nextLine().trim();
		 		finItLength = findIt.length(); 
		 		for (int i = 0; i <= (questionLength - finItLength); i++) {
		 		   if (question.regionMatches(true , i, findIt, 0, finItLength)) {
		 			  System.out.println(question.substring(i, i + finItLength)+ " " + i);
		 			  String resutlSearch = question.substring(i, i + finItLength);
		 			  if(i != 0){
		 				 if(question.substring(i-1, i).equals(" ")){
		 					return true;
		 				 }
		 			  }else return true;		 			  
		 		   }
		 		}
	     }
		 return false;
	}
	public static void main(String[] args){
		 Scanner scanIn=new Scanner(System.in);
		 String question="";
		 System.out.println("Nhập vào câu hỏi:");
		 question = scanIn.nextLine();
		 
		 System.out.println("Câu hỏi của bạn nhập vào là:" + question);
		 int typeQuestion = 0;
		 
		 try {
			if(FindTypeQuestion("nguoi.txt", question)){
				typeQuestion = 1;
			}
			else if(FindTypeQuestion("thoigian.txt", question)){
				typeQuestion = 2;
			}
			else if(FindTypeQuestion("diadiem.txt", question)){
				typeQuestion = 3;
			}
			else{
				typeQuestion = 0;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 switch(typeQuestion){
		 	case 0: System.out.println("Không phân loại được câu hỏi!"); break;
		 	case 1: System.out.println("Câu hỏi về người!"); break;
		 	case 2: System.out.println("Câu hỏi về thời gian!"); break;
		 	case 3: System.out.println("Câu hỏi về địa điểm!"); break;
		 }
		 
		 
		 VietnameseMaxentTagger tagger = new VietnameseMaxentTagger();
		 String tagged = tagger.tagText(question);
		 System.out.println(tagged);
	}
}

package myCRF;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;

public class test0 {
	public static void main(String args[]){
//		String trainFile = "data/train.utf8";
//		String labelFile = "data/labels.utf8";
		String testFile = "data/self/test_self_prose.utf8";
//		String testFile = "data/self/test_self_sina.utf8";
//		String templateFile = "data/template.utf8";
//		int learningTimes = 5;
//		CRF crf = new CRF(trainFile,labelFile,testFile,templateFile,learningTimes);
		ArrayList<String> array = new ArrayList<String>();
		ArrayList<String> array1 = new ArrayList<String>();
		String input = "";
		String input1 = "";
		FileReader fr1 = null;
		try {
			fr1 = new FileReader(testFile);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		BufferedReader bf1 = new BufferedReader(fr1);
		String str1 = "";
		try {
			while(((str1 = bf1.readLine()) != null)){
				if(!str1.isEmpty() && !str1.equals("") && !str1.equals(" ") && !str1.equals("  ")){
//					System.out.println(str1);
					input += str1.charAt(0);
					input1 += str1.charAt(2);
//					System.out.println("!!!!");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(input);
//		List<Term> termList = HanLP.segment(input);
//		System.out.println(termList);
		
//		List<Term> termList = NLPTokenizer.segment(input);
//		System.out.println(termList);
		
		
		
//		String text = input;
//		List<Term> termList = SpeedTokenizer.segment(text);
//		long start = System.currentTimeMillis();
//		int pressure = 1;
//		for (int i = 0; i < pressure; ++i)
//		{
//		    SpeedTokenizer.segment(text);
//		}
//		double costTime = (System.currentTimeMillis() - start) / (double)1000;
//		System.out.printf("分词速度：%.2f字每秒", text.length() * pressure / costTime);
		
//		Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//		Segment shortestSegment = new ViterbiSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//		String[] testCase = new String[]{
//		        input,
//		};
//		List<Term> termList = null;
//		for (String sentence : testCase)
//		{
////			termList = nShortSegment.seg(sentence);
//		    termList = shortestSegment.seg(sentence);
//		}
		
		Segment segment = new CRFSegment();
		segment.enablePartOfSpeechTagging(true);
		List<Term> termList = segment.seg("你看过穆赫兰道吗");
		System.out.println(termList);
		for (Term term : termList)
		{
		    if (term.nature == null)
		    {
		        System.out.println("识别到新词：" + term.word);
		    }
		}
		
		
		for(int i=0; i<termList.size(); i++){
			int len=0;
			for(int u=0;u<100;u++){
				if(String.valueOf(termList.get(i).toString().charAt(u)).equals("/")){
					len = u;
					break;
				}
			}
			
			
			array1.add(termList.get(i).toString().substring(len+1));
			
//			System.out.println(len);
			String temp;
			if(len == 0)
				break;
			if(len == 1){
				temp = termList.get(i).toString().charAt(0) + "S";
				array.add(temp);
			}else if(len == 2){
				temp = termList.get(i).toString().charAt(0) + "B";
				array.add(temp);
				temp = termList.get(i).toString().charAt(1) + "E";
				array.add(temp);
			}else{
				temp = termList.get(i).toString().charAt(0) + "B";
				array.add(temp);
				for(int j=1;j<len-1;j++){
					temp = termList.get(i).toString().charAt(j) + "I";
					array.add(temp);
				}
				temp = termList.get(i).toString().charAt(len-1) + "E";
				array.add(temp);
			}
		}
//		System.out.println(array.toString());
		System.out.println(input.length());
		System.out.println(array.size());
		
		int err = 0;
		for(int i=0;i<array.size();i++){
			if (array.get(i).charAt(1) != input1.charAt(i)){
				err++;
			}
		}
		System.out.println(err);
		System.out.println(1 - err/(double)input.length());
		
		HashMap<String, String> hm = new HashMap<String, String>();
		for(int i=0;i<array1.size();i++){
			hm.put(array1.get(i), "");
		}
		
		System.out.println(hm.size());
		
	}
}

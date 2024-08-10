package makeDataStructures;


public class Main{
    public static void main(String[] args){

        String[] testString = new String[]{"이건","어떻게","써야할까","알고싶다"};

        //새로운 linkedArray를 추가
        LinkedArray test = new LinkedArray(testString);
        test.getlist();

        //중복된 대상에 대해서 추가해보기
        test.set("2","그래서 추가함","드가자");
        test.getlist();

        //중복 없낸 대상 추가해보기
        test.set("","그래서 추가함","드가자");
        test.getlist();

        //연결할 주소를 바꿔준 데이터
        test.set("추가된 자료","그래서 추가함","2");
        test.getlist();

        //주소가 숫자인 데이터
        test.set(10,"그래서 또 추가함","1");
        test.getlist();

        test.sort();

        test.set("1220","마지막 추가 해줘");
        test.getlist();

        test.set("11030","처음에 추가 해줘", true);
        test.getlist();

        test.set("1245","진짜 마지막 추가 해줘", false);
        test.getlist();




    }


}

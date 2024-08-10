package makeDataStructures;

public class LinkedArray {
    private int ArrayLength; //이건 이 클래스에서 사용하는 칸의 수를 말한다. = 반복작업 최대수
    private int i;
    private int[] ArrayintAddress; //이건 그냥 숫자 보여주기용 배열임
    private String[] ArrayAddress; //주소명을 지정하는 겁니다.
    private String[] ArrayData; // 값을 넣을 데이터를 만든다
    private String[] ArrayNextAddress; // 다음이 어디로 이동하는 데이터인지 확인한다.



    // 시스템 관리를 위한 4개의 데이터
    // 1. 기본적으로 부여되는 int 타입의 번호
    // 2. 내가 이름을 지정한 주소
    // 3. 저장하려는 데이터
    // 4. 다음 주소
    //
    // 이걸 첫 생성을 배열로 하면 편하기야 하지.. 배열은 변경이 안되니까
    // 배열이 변경이 안되면? 새로운 배열을 만들어서? 전부다 옮겨 담어
    //기본 생성자임 이제 이걸 숫자로 받았다 라고 하면 다른거 하면 되고
    LinkedArray(String... setArray) {
        ArrayLength = setArray.length;

        ArrayintAddress = new int[ArrayLength]; // 자체적 관리를 위한 데이터 표기
        ArrayAddress = new String[ArrayLength]; // 유저의 임의 지정한 주소
        ArrayData = new String[ArrayLength]; //이것의 타입은 위에서 받는 타입과 동일하게 맞추기
        ArrayNextAddress = new String[ArrayLength]; // 다음 주소를 찾아가기 위한 데이터

        i = 0;

        do{
            ArrayintAddress[i] = i;
            ArrayAddress[i] = Integer.toString(i);
            ArrayData[i] = setArray[i];

            if (i==ArrayLength-1) {
                ArrayNextAddress[i] = "";
                break;
            }else {
                ArrayNextAddress[i] = Integer.toString(i + 1);
            }

            i++;

        }while (i<ArrayLength);

    }



    public enum ExistCode{
        //ExistCheck 쓰는 대상을 매칭시키기 위해 사용
        부재, 존재, 공백, 음수
    }


    private ExistCode ExistCheck(String address){
        boolean goAhead = false;
        ExistCode exist = ExistCode.부재;

        //address가 중복이 있는지 확인해야함. 공백으로 넣지도 못하게 해야함.
        for (i=0; i<ArrayLength; i++){
            //중복에 대한 검사를 진행
            if (address.equals(ArrayAddress[i])){
                //여는 맞다면 boolean값으로 맞다 아니다 추가
                goAhead = true;
            }
        }

        if (goAhead) {
            goAhead=false;
            exist = ExistCode.존재;
            return exist;
        }

        //공백에 대한 검사 1. null 2. ""
        if (address.equals("")){
            exist = ExistCode.공백;
            return exist;
        }

        if (address == null){
            exist = ExistCode.공백;
            return exist;
        }

        return exist;
    }




    private ExistCode ExistCheck(int address){
        boolean goAhead = false;
        ExistCode exist = ExistCode.부재;

        //address가 중복이 있는지 확인해야함. 공백으로 넣지도 못하게 해야함.
        for (i=0; i<ArrayLength; i++){
            //중복에 대한 검사를 진행
            if (address == ArrayintAddress[i]){
                //여는 맞다면 boolean값으로 맞다 아니다 추가
                goAhead = true;
            }
        }

        if (goAhead) {
            goAhead=false;
            exist = ExistCode.존재;
            return exist;
        }

        //공백에 대한 검사 1.  2. ""
        /*
        if (address.equals("")){
            exist = ExistCode.공백;
            return exist;
        }

        if (address == null){
            exist = ExistCode.공백;
            return exist;
        }
        */

        if (address < 0){
            exist = ExistCode.음수;
        }

        return exist;
    }


    //내가 꼬리 물 대상에 대한 데이터를 고쳐야겠지?
    private String getNextAddress(String address, String beforeAddress){

        //데이터를 옮겨 담기 위한 대상을 생성
        String tempString = "";

        //beforeaddress가 address 데이터에 있는지 확인해야함
        for (i=0; i<ArrayLength; i++) {
            if (beforeAddress.equals(ArrayAddress[i])) {
                tempString = ArrayNextAddress[i];
                ArrayNextAddress[i] = address;
            }

        }
        return tempString;
    }


    private String getNextAddress(int address, String beforeAddress){

        //데이터를 옮겨 담기 위한 대상을 생성
        String tempString = "";

        //beforeaddress가 address 데이터에 있는지 확인해야함
        for (i=0; i<ArrayLength; i++) {
            if (beforeAddress.equals(ArrayAddress[i])) {
                tempString = ArrayNextAddress[i];
                ArrayNextAddress[i] = Integer.toString(address);
            }

        }
        return tempString;
    }



    //데이터를 추가하는 메소드
    public void set(String address, String data, String beforeAddress){

        ExistCode howWork = ExistCheck(address);

        switch (howWork){
            case 부재:
                break; //얘는 추가하는 거니까 부재하면 그냥 다음꺼 하면 됌.

            case 존재:
                //이건 존재하면 중복이라는 거잖아. 그럼? 끝내야된다
                System.out.println("중복된 주소가 추가되었습니다.\n다른 주소를 사용해주세요");
                System.out.println();
                return;

            case 공백:
                //이건 존재하면 중복이라는 거잖아. 그럼? 끝내야된다
                System.out.println("주소를 공백으로 사용할 수 없습니다.\n다른 주소를 사용해주세요");
                System.out.println();
                return;

        }

        String nextAddress ="";
        nextAddress = getNextAddress(address,beforeAddress);


        //중복이 없다면 가장 뒷자리에 추가하죠
        //arrayLength +1배열을 생성해서 다 밀어넣고 그걸 다시 원본에 넣기

        ArrayLength++;

        int[] newArrayintAddress = new int[ArrayLength]; // 자체적 관리를 위한 데이터 표기
        String[] newArrayAddress = new String[ArrayLength]; // 유저의 임의 지정한 주소
        String[] newArrayData = new String[ArrayLength]; //이것의 타입은 위에서 받는 타입과 동일하게 맞추기
        String[] newArrayNextAddress = new String[ArrayLength]; // 다음 주소를 찾아가기 위한 데이터

        //새로운 배열을 만들었으니 이제 옮겨줄 차례다
        for (i=0; i<ArrayLength; i++){
            if (i == ArrayLength-1){
                newArrayintAddress[i] = ArrayLength-1;
                newArrayAddress[i] = address;
                newArrayData[i] = data;
                newArrayNextAddress[i] = nextAddress;

            }else{
                newArrayintAddress[i] = ArrayintAddress[i];
                newArrayAddress[i] = ArrayAddress[i];
                newArrayData[i] = ArrayData[i];
                newArrayNextAddress[i] = ArrayNextAddress[i];
            }
        }



        ArrayintAddress = new int[ArrayLength]; // 자체적 관리를 위한 데이터 표기
        ArrayAddress = new String[ArrayLength]; // 유저의 임의 지정한 주소
        ArrayData = new String[ArrayLength]; //이것의 타입은 위에서 받는 타입과 동일하게 맞추기
        ArrayNextAddress = new String[ArrayLength];

        //만약 얘가 마지막 주소에 들어올 경우? 그러면
        //원래 마지막인 얘의 다음 주소 데이터를 요 데이터로 연결해주면 됌.

        ArrayintAddress = newArrayintAddress;
        ArrayAddress = newArrayAddress;
        ArrayData = newArrayData;
        ArrayNextAddress = newArrayNextAddress;


        //이제 여기서 순번 바꿔주기 필요
        //기존에 beforeAddress 였던 대상을 현재 추가된 대상으로 교체해줘야함
    }

    public void set(int address, String data, String beforeAddress){

        ExistCode howWork = ExistCheck(address);

        switch (howWork){
            case 부재:
                break; //얘는 추가하는 거니까 부재하면 그냥 다음꺼 하면 됌.

            case 존재:
                //이건 존재하면 중복이라는 거잖아. 그럼? 끝내야된다
                System.out.println("중복된 주소가 추가되었습니다.\n다른 주소를 사용해주세요");
                System.out.println();
                return;


            case 음수:
                //음수로 존재하는 순번이 없음
                System.out.println("순번이 음수인 경우는 없습니다.\n다른 주소를 사용해주세요");
                System.out.println();
                return;
        }

        String nextAddress;
        nextAddress = getNextAddress(address,beforeAddress);


        //중복이 없다면 가장 뒷자리에 추가하죠
        //arrayLength +1배열을 생성해서 다 밀어넣고 그걸 다시 원본에 넣기

        ArrayLength++;

        int[] newArrayintAddress = new int[ArrayLength]; // 자체적 관리를 위한 데이터 표기
        String[] newArrayAddress = new String[ArrayLength]; // 유저의 임의 지정한 주소
        String[] newArrayData = new String[ArrayLength]; //이것의 타입은 위에서 받는 타입과 동일하게 맞추기
        String[] newArrayNextAddress = new String[ArrayLength]; // 다음 주소를 찾아가기 위한 데이터

        //새로운 배열을 만들었으니 이제 옮겨줄 차례다
        for (i=0; i<ArrayLength; i++){
            if (i == ArrayLength-1){
                newArrayintAddress[i] = ArrayLength-1;
                newArrayAddress[i] = Integer.toString(address);
                newArrayData[i] = data;
                newArrayNextAddress[i] = nextAddress;

            }else{
                newArrayintAddress[i] = ArrayintAddress[i];
                newArrayAddress[i] = ArrayAddress[i];
                newArrayData[i] = ArrayData[i];
                newArrayNextAddress[i] = ArrayNextAddress[i];
            }
        }



        ArrayintAddress = new int[ArrayLength]; // 자체적 관리를 위한 데이터 표기
        ArrayAddress = new String[ArrayLength]; // 유저의 임의 지정한 주소
        ArrayData = new String[ArrayLength]; //이것의 타입은 위에서 받는 타입과 동일하게 맞추기
        ArrayNextAddress = new String[ArrayLength];

        //만약 얘가 마지막 주소에 들어올 경우? 그러면
        //원래 마지막인 얘의 다음 주소 데이터를 요 데이터로 연결해주면 됌.

        ArrayintAddress = newArrayintAddress;
        ArrayAddress = newArrayAddress;
        ArrayData = newArrayData;
        ArrayNextAddress = newArrayNextAddress;


        //이제 여기서 순번 바꿔주기 필요
        //기존에 beforeAddress 였던 대상을 현재 추가된 대상으로 교체해줘야함
    }


    public void set(String address, String data, boolean position){
        //요건 ture면 첫위치, false면 마지막 위치
        //address가 중복이 안되고 기본데이터를 생각해서 채워줘야함
        //이건 중복 생각할 필요 없음. 그냥 숫자 자동으로 채워버리면 됌.
        //arrayLength +1배열을 생성해서 다 밀어넣고 그걸 다시 원본에 넣기


        ExistCode howWork = ExistCheck(address);

        switch (howWork){
            case 부재:
                break; //얘는 추가하는 거니까 부재하면 그냥 다음꺼 하면 됌.

            case 존재:
                //이건 존재하면 중복이라는 거잖아. 그럼? 끝내야된다
                System.out.println("중복된 주소가 추가되었습니다.\n다른 주소를 사용해주세요");
                System.out.println();
                return;

            case 공백:
                //이건 존재하면 중복이라는 거잖아. 그럼? 끝내야된다
                System.out.println("주소를 공백으로 사용할 수 없습니다.\n다른 주소를 사용해주세요");
                System.out.println();
                return;

            case 음수:
                //음수로 존재하는 순번이 없음
                System.out.println("순번이 음수인 경우는 없습니다.\n다른 주소를 사용해주세요");
                System.out.println();
                return;
        }


        if (position){
            /*true면 첫 위치에 생성해하는거니까
             첫 데이터 주소를 next 위치에 넣어주고
             새로 추가한거 쭉 넣으면 됌
            */
            sort();

            //정렬이 됐으면
            //첫 데이터 이후를 싹 밀고
            //얘를 추가로 넣어주면 된다.

            ArrayLength++;

            int[] newArrayIntAddress = new int[ArrayLength];
            String[] newArrayAddress = new String[ArrayLength];
            String[] newArrayData = new String[ArrayLength];
            String[] newArrayNextAddress = new String[ArrayLength];

            for (i=ArrayLength-1; i>-1; i--){

                newArrayIntAddress[i] = i;

                //첫데이터는 바로 추가
                if (i == 0){

                    newArrayAddress[0] = address;
                    newArrayData[0] = data;
                    newArrayNextAddress[0] = ArrayAddress[0];

                }else{
                    //나머지 데이터는 그대로 받아오기
                    newArrayAddress[i] = ArrayAddress[i-1];
                    newArrayData[i] = ArrayData[i-1];
                    newArrayNextAddress[i] = ArrayNextAddress[i-1];

                }

            }

            ArrayintAddress = new int[ArrayLength];
            ArrayAddress = new String[ArrayLength];
            ArrayData = new String[ArrayLength];
            ArrayNextAddress = new String[ArrayLength];

            ArrayintAddress = newArrayIntAddress;
            ArrayData = newArrayData;
            ArrayAddress = newArrayAddress;
            ArrayNextAddress = newArrayNextAddress;




        }else{
            /*false면 마지막 위치에 생성해하는거니까
             next 데이터가 none인 대상을 찾아서
             이거에 부여할 주소를 자동으로 작성해주면 됌.
             새로 추가한거 쭉 넣으면 됌
            */
            set(address,data);
        }


    }

    public void set(String address, String data){
        //요건 마지막 위치로 추가하는 메소드
        //중복 검사
        ExistCode howWork = ExistCheck(address);
        switch (howWork){
            case 부재:
                break; //얘는 추가하는 거니까 부재하면 그냥 다음꺼 하면 됌.

            case 존재:
                //이건 존재하면 중복이라는 거잖아. 그럼? 끝내야된다
                System.out.println("중복된 주소가 추가되었습니다.\n다른 주소를 사용해주세요");
                System.out.println();
                return;

            case 공백:
                //이건 존재하면 중복이라는 거잖아. 그럼? 끝내야된다
                System.out.println("주소를 공백으로 사용할 수 없습니다.\n다른 주소를 사용해주세요");
                System.out.println();
                return;

            case 음수:
                //음수로 존재하는 순번이 없음
                System.out.println("순번이 음수인 경우는 없습니다.\n다른 주소를 사용해주세요");
                System.out.println();
                return;
        }

        for (i=0;i<ArrayLength;i++){
            if(ArrayNextAddress[i].isEmpty()){
                ArrayNextAddress[i] = address;
                break;
            }
        }

        ArrayLength++;
        int[] newArrayintAddress = new int[ArrayLength];
        String[] newArrayAddress = new String[ArrayLength];
        String[] newArrayData = new String[ArrayLength];
        String[] newArrayNextAddress = new String[ArrayLength];

        for (i=0; i<ArrayLength; i++){
            if (i == ArrayLength-1){
                newArrayintAddress[i] = ArrayLength-1;
                newArrayAddress[i] = address;
                newArrayData[i] = data;
                newArrayNextAddress[i] = "";
            }else{
                newArrayintAddress[i] = ArrayintAddress[i];
                newArrayAddress[i] = ArrayAddress[i];
                newArrayData[i] = ArrayData[i];
                newArrayNextAddress[i] = ArrayNextAddress[i];
            }
        }

        ArrayintAddress = new int[ArrayLength];
        ArrayAddress = new String[ArrayLength];
        ArrayData = new String[ArrayLength];
        ArrayNextAddress = new String[ArrayLength];

        ArrayintAddress = newArrayintAddress;
        ArrayAddress = newArrayAddress;
        ArrayData = newArrayData;
        ArrayNextAddress = newArrayNextAddress;


        //address가 중복이 안되고 기본데이터를 생각해서 채워줘야함
        //중복이 없다면 가장 뒷자리에 추가하고 다음 주소칸을 바꿔주면 될 뿐이다.
        //arrayLength +1배열을 생성해서 다 밀어넣고 그걸 다시 원본에 넣기
    }



    public void reData(String address, String data){
        //address 있는지 확인하고 없으면 오류 내주고
        //기존 데이터 없애고 이 데이터를 새로 입력해주면 되다.
    }

    public void reData(int address, String data){
        //address 있는지 확인하고 없으면 오류 내주고
        //기존 데이터 없애고 이 데이터를 새로 입력해주면 되다.
    }

    //주소명 변경 readrres(원래 주소, 변경할 주소)
    public void reAddress(String addressName, String changeAddress){
        //요 주소를 저 주소로 이름을 바꿔주세요
    }
    public void reAddress(int addressName, String changeAddress){
        //요 주소를 저 주소로 이름을 바꿔주세요
    }

    //데이터의 위치 변경 trance(변경대상주소, 어느 주소 뒤로? = 만약 없으면 첫주소로 옮기기)
    public void trance(String addressName, String beforeAddress){
        //이 주소의 데이터를 이 주소의 뒤로 옮겨 주세요
    }

    public void trance(int addressName, String beforeAddress){
        //이 주소의 데이터를 이 주소의 뒤로 옮겨 주세요
    }

    public void trance(String addressName){
        //이 주소의 데이터를 첫자리로 옮겨주세요
    }

    public void trance(int addressName){
        //이 주소의 데이터를 첫자리로 옮겨주세요
    }
    public void remove(String address){
        //해당 주소의 위치를 삭제하고 나머지는 새로운 배열로 옮겨 담아야한다.
    }


    //int 형 String형 둘 다 만들기
    public void remove(int address){
        /*
        int address Integer.toString(address)로 받고
        해당 주소의 위치를 삭제하고 나머지는 새로운 배열로 옮겨 담아야한다.
        */

        //이걸 하는 방법으로 마지막 대상이랑 위치를 바꿔
        // 그리고 마지막 대상을 지워
        // 옮겨 담아
        

    }




    public void getlist(){

        System.out.println("순번, 주소명, 값, 다음 주소");

        for (i=0;i<ArrayLength;i++){
            System.out.println(ArrayintAddress[i] + " | " + ArrayAddress[i] + " | " + ArrayData[i] + " | " + ArrayNextAddress[i]);
        }
        System.out.println();
    }

    public void get(String... list){
        //list에 해당하는 주소의 데이터를 보여주세요

    }
    public void get(int... list){
        //list에 해당하는 주소의 데이터를 보여주세요
    }
    /*
        get(String, int, boolean)
     * 참이면 특정 지점에서 n번째 떨어진 대상을 알려주는 코드
     * 거짓이면 해당 위치부터 n번째까지 알려주는 코드
     */
    public void get(String addressName, int showLength, boolean takeAll){
        /*
        takeAll 변수값에 따라서 특정 위치에서 n번째 떨어진 데이터만 보여줄 것인지
        혹은 특정 변수 위치부터 n번째 떨어진 데이터까지 전부 보열줄 것인지.
        일단 참이면 다 보여주고 거짓이면 1개만 보여줄거임
        그리고 기본값을 거짓이니까 숨겨놓은 것도 만들어 둬야함.
         */

    }


    public void get(String addressName, int showLength){
        /*
        takeAll 변수값에 따라서 특정 위치에서 n번째 떨어진 데이터만 보여줄 것인지
        혹은 특정 변수 위치부터 n번째 떨어진 데이터까지 전부 보열줄 것인지.
        일단 참이면 다 보여주고 거짓이면 1개만 보여줄거임
        그리고 기본값을 거짓이니까 숨겨놓은 것도 만들어 둬야함.
         */

    }

    public void sort(){
        //추가하거나 제거하거나 하면 이상해 지잖어? 그럼 이걸 정렬해서 볼 수 있게 해줘야 겠지?
        //아무튼 위치에 맞춰서 작성할 수 있게 하면 되는거잖어?
        //다음 주소 다음에 그 주소가 와야함.
        //를 하려했으나.. 그건 첫 데이터를 찾을 수 없으니.. 끝데이터를 먼저 찾고
        //역순으로 다시 데이터를 대입하면 되겠다.

        //nextAddress가 없는 대상을 가장 마지막 대상으로 바꿔요.
        //첫 데이터를 고정시키는 과정입니다.
        for(int i=0;i<ArrayLength;i++){

            if(ArrayNextAddress[i].equals("")){
                //i와 ArrayLength -1에 해당하는 좌표를 데이터 교체해줘야함.
                String tempAddress = ArrayAddress[ArrayLength-1];
                String tempData = ArrayData[ArrayLength-1];
                String tempNextAddress = ArrayNextAddress[ArrayLength-1];

                ArrayAddress[ArrayLength-1] = ArrayAddress[i];
                ArrayData[ArrayLength-1] = ArrayData[i];
                ArrayNextAddress[ArrayLength-1] = "";

                ArrayNextAddress[i] = tempNextAddress;
                ArrayAddress[i] = tempAddress;
                ArrayData[i] = tempData;

            }

        }

        //이후 데이터를 정렬하면 된다.
        for (int i=ArrayLength-1;i>0;i--){
            for(int j=i-1;j>-1;j--){

                if(ArrayNextAddress[j].equals(ArrayAddress[i])){

                    String tempAddress = ArrayAddress[i-1];
                    String tempData = ArrayData[i-1];
                    String tempNextAddress = ArrayNextAddress[i-1];

                    ArrayAddress[i-1] = ArrayAddress[j];
                    ArrayData[i-1] = ArrayData[j];
                    ArrayNextAddress[i-1] = ArrayNextAddress[j];

                    ArrayNextAddress[j] = tempNextAddress;
                    ArrayAddress[j] = tempAddress;
                    ArrayData[j] = tempData;

                }
            }
        }



    }

}
/**
 * 이 대상은 받을 수 있는 타입이 여러 종류다 - 이건 나중에
 *
 * 받은 타입에 대해서 각각 설정이 되어야 한다.
 * 생성자로 받은 변수의 형태에 따라서 모양을 바꿔줘야...하나..?
 * 아니면 이걸 전부 String으로 받게 하면 되는거 아닌가?
 *
 *
 * 초기생성
 * 이 친구는 특정 데이터를 받으면 그 배열의 수 만큼 반복해서 데이터 칸을 만들어야함.
 * 데이터 칸을 만들고, 거기에 부여된 이름과 데이터를 저장함. 다음 데이터가 있는지 확인해야하나?
 *
 *
 *
 * 후속 조치
 * 데이터의 추가 set(해당 주소 이름, 데이터, 어느 주소 뒤로? 만약 없으면 첫주소로 만들기)
 * 데이터의 제거 del(지울 데이터 주소)
 * 데이터의 변경 redata(주소, 데이터)
 * 주소명 변경 readrres(원래 주소, 변경할 주소)
 * 데이터의 위치 변경 trance(변경대상주소, 어느 주소 뒤로? = 만약 없으면 첫주소로 옮기기)
 * 데이터의 추출
 * getlist 생성자 없으면 전체 보여주기
 * get(String...) 원하는거 골라서 보여주기
 * get(String, int, boolean)
 * 참이면 특정 지점에서 n번째 떨어진 대상을 알려주는 코드
 * 거짓이면 해당 위치부터 n번째까지 알려주는 코드
 *
 * sort
 * 이게 다음 데이터 위치가 진짜로 다음데이터 위치로 올 수 있게 데이터를 변경
 *
 *
 */
package makeDataStructures;

import java.security.PrivateKey;

public class LinkArray {
    baguni[] LinkedArray;
    //LinkedArray를 바구니의 배열로 사용
    int arrayLength=0; // 이건 baguni의 갯수를 표현하기 위해서 사용


    //나는 바구니 라는 클래스를 이용해서 이 링크드 어레이를 구현하겠다.
    public class baguni{
        /*
        여기에는 3가지의 속성이 필요하다
        1. 주소
        2. 값
        3. 다음 주소
        이것을 표현하기 위해서 get과 set을 설정하자
         */
        private int sequence = 0; //얘가 몇번째 순번인지 추가하는 대상, 순전히 관리용
        private String address = "";//이건 주소
        private String dataValue = "";//값을 넣을 데이터
        private baguni nextbaguni = null;//다음 데이터에 대한 정보

        //생성자를 통해서 이 데이터를 채워보자
        baguni(String address, String dataValue){
            this.sequence = 0;
            this.address = address;
            this.dataValue = dataValue;
        }

        public void setSequence(int sequence){
            this.sequence = sequence;
        }
        public int getSequence(){
            return sequence;
        }


        public void setAddress(String address){
            this.address =address;
        }

        public String getAddress(){
            return address;
        }


        public void setDataValue(String dataValue){
            this.dataValue = dataValue;
        }

        public String getDataValue(){
            return dataValue;
        }


        public void setNextbaguni(baguni nextbaguni){
            this.nextbaguni = nextbaguni;
        }

        public baguni getNextbaguni(){
            return nextbaguni;
        }


    } //baguni class에 대한 정의 끝


    LinkArray(int Length){
        arrayLength=Length;
        LinkedArray=new baguni[arrayLength];
    }

    
    public LinkArray add(String address, String dataValue){
        arrayLength++;
        baguni baguni = new baguni(address,dataValue);


        return this;
    }



}

/*
구조에 대해서 작성
1. 링크드 어레이는 바구니 클래스의 배열을 다루는 클래스이다.
2. 링크드 어레이는 바구니 클래스를 자체적으로 정의 한다.
3. 바구니 클래스는 하나의 객체이며, 이 안에 data, 그리고 address(특정 바구니를 호출하기 위한 방법)대한 정보가 포함되어 있다.
4. 바구니는 다음 바구니에 대한 정보를 가지고 있어야 하며, 이것은 바구니 클래스로 지정할 수 있다.
5. 자기 자신을 반환하는 방법으로 메소드 체이닝을 사용할 수 있다.
6. 여기서 필요한 기능은
  - 추가
  - 제거
  - 변경
  - 데이터의 확인
  - 특정 데이터를 기준으로 n번째 이후 값 > 이건 정해진 순서가 없으니까 확인이 필요함
  - 정렬 기능? 이건 고민해보죠

7. 따라서 위 기능들을 바구니를 다루는 클래스인 링크드 어레이로 포장하여 다루는 팩 형태의 데이터라고 생각하면 된다.
 */
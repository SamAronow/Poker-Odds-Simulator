public class j {
    public static void main(String[] args){
        int[] numArr = {1,3,5,7,9};
        for (int k=0; k<numArr.length/2; k++){
            int temp = numArr[k];
            numArr[k] = numArr[numArr.length - k-1];
            numArr[numArr.length - k -1] = temp;
        }
        for (int x : numArr){
            System.out.println(x);
        }
    }
}

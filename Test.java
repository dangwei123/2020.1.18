给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。
class Solution {
    public boolean isPalindrome(String s) {
        int left=0;
        int right=s.length()-1;
        while(left<right){
            char c1=s.charAt(left);
            char c2=s.charAt(right);
            boolean b1=(c1>='A'&&c1<='Z')||(c1>='a'&&c1<='z');
            boolean b2=(c2>='A'&&c2<='Z')||(c2>='a'&&c2<='z');
            boolean b3=(c1>='0'&&c1<='9');
            boolean b4=(c2>='0'&&c2<='9');
            if((b1&&b2)||(b3&&b4)){
                boolean b5=((int)Math.abs((int)c1-(int)c2))==32;
                boolean b6=((int)Math.abs((int)c1-(int)c2))==0;
                if(b6||b5){
                    left++;
                    right--;
                    continue;
                }else{
                    return false;
                }
            }
            if((b1&&b4)||(b2&&b3)){
                return false;
            }
            if((!b1)&&(!b3)){
                left++;
            }if((!b2)&&(!b4)){
                right--;
            }         
          
        }
        return true;
    }
}

给定一组字符，使用原地算法将其压缩。

压缩后的长度必须始终小于或等于原数组长度。

数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。

在完成原地修改输入数组后，返回数组的新长度。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/string-compression
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int compress(char[] chars) {
        int l=0;
        int count=0;
        for(int r=0;r<=chars.length;r++){
            if(r==chars.length||chars[r]!=chars[l]){
                chars[count++]=chars[l];
                if(r-l>1){
                    for(char c:String.valueOf(r-l).toCharArray()){
                        chars[count++]=c;
                    }
                }
                l=r;
            }
        }
        return count;
    }
}

给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

你找到的子数组应是最短的，请输出它的长度。
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] tmp=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            tmp[i]=nums[i];
        }
        Arrays.sort(tmp);
        int count=0;
        int l=0;
        int r=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=tmp[i]){
                l=i;
                break;
            }
        }
         for(int i=nums.length-1;i>=0;i--){
            if(nums[i]!=tmp[i]){
                r=i;
                break;
            }
        }
        return r-l>=1?r-l+1:0;
    }
}

根据逆波兰表示法，求表达式的值。

有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack();
        for(int i=0;i<tokens.length;i++){
            String s=tokens[i];
            if(s.equals("+")){
                int a=stack.pop();
                if(!stack.empty()){
                    int b=stack.pop();
                    stack.push(b+a);
                }else{
                    int b=func(tokens[++i]);
                    stack.push(a+b);
                }
                
            }else if(s.equals("-")){
                int a=stack.pop();
                if(!stack.empty()){
                    int b=stack.pop();
                    stack.push(b-a);
                }else{
                    int b=func(tokens[++i]);
                    stack.push(a-b);
                }
            }else if(s.equals("*")){
                int a=stack.pop();
                if(!stack.empty()){
                    int b=stack.pop();
                    stack.push(b*a);
                }else{
                    int b=func(tokens[++i]);
                    stack.push(a*b);
                }
            }else if(s.equals("/")){
                int a=stack.pop();
                if(!stack.empty()){
                    int b=stack.pop();
                    stack.push(b/a);
                }else{
                    int b=func(tokens[++i]);
                    stack.push(a/b);
                }
            }else{
                stack.push(func(s));
            }
        }
        return stack.peek();
    }

    private int func(String s){
        int sum=0;
        if(s.charAt(0)=='-'){
            for(int i=1;i<s.length();i++){
                char c=s.charAt(i);
                sum=sum*10+c-'0';
            }
            return -sum;
        }
        for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                sum=sum*10+c-'0';
        }
        return sum;
    }
}


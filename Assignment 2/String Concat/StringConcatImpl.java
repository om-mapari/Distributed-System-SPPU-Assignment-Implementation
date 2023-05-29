import StringConcatModule.StringConcatPOA;

class StringConcatImpl extends StringConcatPOA {
    StringConcatImpl() {
        super();
        System.out.println("StringConcat Object Created");
    }

    public String concatenate_strings(String str1, String str2) {
        return str1 + str2;
    }
}


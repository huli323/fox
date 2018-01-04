package jpa.service;

public class EmpOps {
    private static EmpOps empOps = null;

    private EmpOps() {
    }

    public static EmpOps getInstance(){
        return empOps == null ? new EmpOps() : empOps;
    }

    public <T> void update(Class<T> c){

    }

    public void create(){}
}

package test

fun main(args: Array<String>){
    var a = arrayOf("a", "b", "c")
    fun iter(){
        a.forEach {
            if(it == "b") return
            print(it)
        }
    }
    iter()
}
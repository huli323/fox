package leetCode;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * 1、Only one letter can be changed at a time
 * 2、Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *
 * Note:
 * ·Return an empty list if there is no such transformation sequence.
 * ·All words contain only lowercase alphabetic characters.
 * ·You may assume no duplicates in the word list.
 * ·You may assume beginWord and endWord are non-empty and are not the same.
 */
public class WordLadderII {
    int min = Integer.MAX_VALUE;
    List<List<String>> result;
    Map<String, Integer> ladders;// 到达每个单词的最短步数
    Map<String, List<String>> map;// 到每个单词最短步数的前一个单词

    public static void main(String[] args) {
        WordLadderII wordLadder = new WordLadderII();
//        List<String> list1 = new ArrayList<>();
//        list1.add("hot");
//        list1.add("dot");
//        list1.add("dog");
//        list1.add("lot");
//        list1.add("log");
//        list1.add("cog");
//        List<List<String>> ladders = wordLadder.findLadders("hit", "cog", list1);
//        wordLadder.out(ladders);


//        List<String> list2 = new ArrayList<>();
//        list2.add("a");
//        list2.add("b");
//        list2.add("c");
//        List<List<String>> ladders2 = wordLadder.findLadders("a", "c", list2);
//        wordLadder.out(ladders2);


//        List<String> list3 = new ArrayList<>();
//        list3.add("hot");
//        list3.add("dot");
//        list3.add("dog");
//        list3.add("lot");
//        list3.add("log");
//        List<List<String>> ladders3 = wordLadder.findLadders("hit", "cog", list3);
//        wordLadder.out(ladders3);


//        List<String> list4 = new ArrayList<>();
//        list4.add("hot");
//        list4.add("cog");
//        list4.add("dog");
//        list4.add("tot");
//        list4.add("hog");
//        list4.add("hop");
//        list4.add("pot");
//        list4.add("dot");
//        List<List<String>> ladders4 = wordLadder.findLadders("hot", "dog", list4);
//        wordLadder.out(ladders4);


//        List<String> list5 = new ArrayList<>();
//        String[] arr = new String[]{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
//        Collections.addAll(list5, arr);
//        List<List<String>> ladders5 = wordLadder.findLadders("cet", "ism", list5);
//        wordLadder.out(ladders5);


        List<String> list6 = new ArrayList<>();
        String[] arr2 = new String[]{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
        Collections.addAll(list6, arr2);
        List<List<String>> ladders6 = wordLadder.find("cet", "ism", list6);
        wordLadder.out(ladders6);

    }

    private List<List<String>> find(String beginWord, String endWord, List<String> wordList) {
        long start = System.currentTimeMillis();
        wordList.remove(beginWord);
        result = new ArrayList<>();

        if(wordList.size() == 0)
            return result;

        map = new HashMap<>();

        ladders = new HashMap<>();
        for(String s : wordList){
            ladders.put(s, Integer.MAX_VALUE);
        }
        ladders.put(beginWord, 0);

        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        while(!queue.isEmpty()){
            String word = queue.poll();
            int step = ladders.get(word) + 1;
            if(step > min)
                continue;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                for (char j = 'a'; j <= 'z'; j++) {
                    sb.setCharAt(i, j);
                    String newWord = sb.toString();
                    if(ladders.containsKey(newWord)){
                        // 检查最短步数
                        if(step > ladders.get(newWord)) {
                            continue;
                        } else if(step < ladders.get(newWord)){ // 更新到该单词最小步数并将其加到队列中（步数等于最小步数说明队列中有过该单词，不重复添加）
                            queue.add(newWord);
                            ladders.put(newWord, step);
                        }

                        if(map.containsKey(newWord)){
                            map.get(newWord).add(word);
                        } else {
                            List<String> list = new ArrayList<>();
                            list.add(word);
                            map.put(newWord, list);
                        }

                        if(newWord.equals(endWord)){
                            min = step;
                        }

                    }
                }
            }
        }

        List<String> resList = new ArrayList<>();
        search(beginWord, endWord, resList);

        System.out.println(System.currentTimeMillis() - start);
        return result;
    }

    private void search(String beginWord, String endWord, List<String> list){
        if(beginWord.equals(endWord)){
            list.add(0, beginWord);
            result.add(new ArrayList<>(list));
            list.remove(beginWord);
            return;
        }

        list.add(0, endWord);
        if(map.containsKey(endWord)) {
            List<String> endWordList = map.get(endWord);
            for (String s : endWordList) {
                search(beginWord, s, list);
            }
        }

        list.remove(0);

    }

    /**
     * 超时
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    private List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        long start = System.currentTimeMillis();
        wordList.remove(beginWord);
        result = new ArrayList<>();

        if(wordList.size() == 0)
            return result;

        ladders = new HashMap<>();
        for(String s : wordList){
            ladders.put(s, Integer.MAX_VALUE);
        }

        List<String> res = new ArrayList<>();
        res.add(beginWord);

        if(wordList.size() > 20){
            deepFindEnormous(endWord, beginWord, -1, wordList, res);
        } else {
            deepFindLess(endWord, beginWord, -1, wordList, res);
        }

        System.out.println(System.currentTimeMillis() - start);
        return result;
    }

    /**
     * 超时
     * @param end
     * @param current
     * @param index
     * @param wordList
     * @param res
     */
    private void deepFindLess(String end, String current, int index, List<String> wordList, List<String> res){
        for (int i = 0; i < current.length(); i++) {
            // 不连续匹配相同位置两次
            if(i == index) continue;

            List<String> tmpRes = new ArrayList<>(res);

            // 在单词列表中匹配只有 i位置字母不同的单词
//            String reg = current.substring(0, i) + "[^"+ current.charAt(i) +"]" + current.substring(i + 1, current.length());
            StringBuilder sb = new StringBuilder(current);
            sb.replace(i, i + 1, "[^"+ current.charAt(i) +"]");
            for(String s : wordList){
                if(s.matches(sb.toString())){
                    // 该步到单词 s的步数大于到到 s的最短步数或结果的步数则跳过
                    if(tmpRes.size() >= ladders.get(s))
                        continue;

                    tmpRes.add(s);
                    // 更新到达单词 s的最短步数
                    ladders.put(s, tmpRes.size());

//                    System.out.println("wordList：" + print(wordList));
//                    System.out.println("     " + current + "替换成" + s + "\n     结果集：" + print(tmpRes));

                    if(s.equals(end)) {
                        updateResult(tmpRes, getMin());
                        return;
                    }

                    if(tmpRes.size() > getMin()) {
                        tmpRes.remove(s);
                        continue;
                    }

                    List<String> copyTmpRes = new ArrayList<>(tmpRes);

                    List<String> copyList = new ArrayList<>(wordList);
                    copyList.remove(s);

                    deepFindLess(end, s, i, copyList, copyTmpRes);

                    tmpRes.remove(s);
                }
            }
        }
    }

    private void deepFindEnormous(String end, String current, int index, List<String> wordList, List<String> res){
        if(wordList.size() < 20){
            deepFindLess(end, current, index, wordList, res);
        }

        for (int i = 0; i < current.length(); i++) {
            // 不连续匹配相同位置两次
            if(i == index) continue;

            List<String> tmpRes = new ArrayList<>(res);

            // 在单词列表中匹配只有 i位置字母不同的单词
            for(char c = 'a'; c <= 'z'; c ++){
                StringBuilder sb = new StringBuilder(current);
                if(c == sb.charAt(i))
                    continue;
                sb.setCharAt(i, c);
                String s = sb.toString();
                if(ladders.containsKey(s)){
                    // 该步到单词 s的步数大于到到 s的最短步数或结果的步数则跳过
                    if(tmpRes.size() >= ladders.get(s))
                        continue;

                    tmpRes.add(s);
                    // 更新到达单词 s的最短步数
                    ladders.put(s, tmpRes.size());

//                    System.out.println("wordList：" + print(wordList));
//                    System.out.println("     " + current + "替换成" + s + "\n     结果集：" + print(tmpRes));

                    if(s.equals(end)) {
                        updateResult(tmpRes, getMin());
                        return;
                    }

                    if(tmpRes.size() > getMin()) {
                        tmpRes.remove(s);
                        continue;
                    }

                    List<String> copyTmpRes = new ArrayList<>(tmpRes);

                    List<String> copyList = new ArrayList<>(wordList);
                    copyList.remove(s);

                    deepFindEnormous(end, s, i, copyList, copyTmpRes);

                    tmpRes.remove(s);
                }
            }
        }
    }

    private int getMin(){
        if(result.isEmpty())
            return Integer.MAX_VALUE;

        return result.get(0).size();
    }

    private void updateResult(List<String> ret, int min){
        if(ret.size() < min) {
            result.clear();
            result.add(ret);
        } else if(ret.size() == min) {
            result.add(ret);
        }
    }


    private String print(List<String> list){
        StringBuilder sb = new StringBuilder();
        for(String s : list){
            sb.append(s + " -> ");
        }
        sb.delete(sb.length() - 4, sb.length());
        return sb.toString();
    }

    private void out(List<List<String>> ladders) {
        StringBuilder sb = new StringBuilder("[");
        for(List<String> l : ladders){
            if(l.size() > 0){
                sb.append("\n[");
            }
            for(String s : l){
                sb.append(s + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
        }
        sb.append("\n");
        sb.append("]");
        System.out.println("===================================================");
        System.out.println(sb.toString());
    }
}

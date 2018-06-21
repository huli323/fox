package leetCode;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        Solution solution = new Solution();
        List<String> list6 = new ArrayList<>();
        String[] arr2 = new String[]{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
        Collections.addAll(list6, arr2);
        List<List<String>> ladders6 = solution.findLadders("cet", "ism", list6);
        System.out.println(System.currentTimeMillis() - start);
        solution.out(ladders6);
    }



    static class DicNode {

        final DicNode nexts[] = new DicNode[26];
        ListNode listNode;

        DicNode nextOrCreate(char c) {
            int charIdx = c - 'a';
            DicNode next = nexts[charIdx];
            if (next == null) {
                next = nexts[charIdx] = new DicNode();
            }
            return next;
        }

        DicNode next(char c) {
            return nexts[c - 'a'];
        }
    }

    static class WordInfo {
        final String word;
        final DicNode[] dicNodes;
        int step;
        ListNode preWordNode;

        WordInfo(String word, DicNode[] dicNodes) {
            this.word = word;
            this.dicNodes = dicNodes;
        }
    }

    static class ListNode {
        final ListNode next;
        final WordInfo info;

        ListNode(WordInfo info, ListNode next) {
            this.info = info;
            this.next = next;
        }
    }

    private List<List<String>> buildWordList(WordInfo info) {
        List<List<String>> lists = new LinkedList();
        dfsBuildList(lists, info, null);
        return lists;
    }

    private void dfsBuildList(List<List<String>> lists, WordInfo info, ListNode resNode) {
        if(info == null) {
            LinkedList<String> list = new LinkedList();
            for(; resNode != null; resNode = resNode.next) {
                list.add(resNode.info.word);
            }
            lists.add(list);
            return;
        }
        resNode = new ListNode(info, resNode);
        for(ListNode node = info.preWordNode; node != null; node = node.next) {
            dfsBuildList(lists, node.info, resNode);
        }
    }

    private DicNode[] getDicNodes(DicNode[] roots, String wordStr) {
        char word[] = wordStr.toCharArray();
        int wordLen = word.length;
        DicNode[] dicNodes = new DicNode[wordLen];
        for (int i = 0; i < wordLen; i++) {
            DicNode node = roots[i];
            for (int j = 0; j < wordLen; j++) {
                if (j != i) {
                    node = node.nextOrCreate(word[j]);
                }
            }
            dicNodes[i] = node;
        }
        return dicNodes;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int wordLen = beginWord.length();
        DicNode roots[] = new DicNode[wordLen];
        for (int i = 0; i < wordLen; i++) {
            roots[i] = new DicNode();
        }
        for (String word : wordList) {
            DicNode[] dicNodes = getDicNodes(roots, word);
            WordInfo info = new WordInfo(word, dicNodes);
            for(DicNode dicNode : dicNodes) {
                dicNode.listNode = new ListNode(info, dicNode.listNode);
            }
        }
        WordInfo begin = new WordInfo(beginWord, getDicNodes(roots, beginWord));
        begin.preWordNode = new ListNode(null, null);
        begin.step = 1;
        WordInfo[] queue = new WordInfo[wordList.size() + 1];
        queue[0] = begin;
        for (int qHead = 0, qTail = 1; qHead < qTail; qHead++) {
            WordInfo info = queue[qHead];
            String word = info.word;
            int step = info.step;
            if (word.equals(endWord)) {
                return buildWordList(info);
            }
            for (DicNode dicNode : info.dicNodes) {
                ListNode node = dicNode.listNode;
                for (; node != null; node = node.next) {
                    WordInfo newInfo = node.info;
                    if (newInfo.step == 0) {
                        newInfo.step = step + 1;
                        queue[qTail++] = newInfo;
                    }
                    if(newInfo.step > step) {
                        newInfo.preWordNode = new ListNode(info, newInfo.preWordNode);
                    }
                }
            }
        }
        return new ArrayList();
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

package aoc22;

import java.io.IOException;

public class Day6b {
	private static String example1 = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
	private static String example2 = "bvwbjplbgvbhsrlpgdmjqwftvncz";
	private static String example3 = "nppdvjthqldpwncqszvftbrmjlhg";
	private static String example4 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
	private static String example5 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
	private static String real = "llqnqffqsqttfffbcfcbcbdcczccfssvwswrwddzlddpdhdwwlvlffjllnjjwjqwjjttwbwcwfccdmmnddgvvpwvvgsshnshsgglljfjzjpjfpfjpplddjcchdhvhlhvllvflfbllsdllgppwjjprjpjrrdwrdrggjvjppgbgttdppwhhcshsvvgpvggsllstsggdjdmjjrvjjszjsjbbsffjwjnwwzjjjvqvfftbffbpffndfdzfdfvdfdggmpmbbwgbgnnbtnnnhggdmdffrqrlrhrzzrmzzmbzzcdcwwzffsrrnfnvfnnvppwjjndjnndtdppgcppsmppljlpjjmlldlsltlglwgwcwnwvwddzrrllwjjnvjvwvppjssncnfcnfcfcczfccpjphjphjjjsgszzhthghjhrjrbrtrjrhrsrfftfzftfmmwmpmgghbggjrrsdswddtjjvnnrwrzrpzzlglwggrnrgrfftnffwwgllrqqzbqbbtltbbgdgpgphggspggplggmcmscsffzcfzzbggdrgrqgrrnlrnrbnnzsnnzcctvvnvwvnwnhhwpwtptllpflfcfttwtjjhwjhhbwhbbtppwhwvhvghvhphpwwcgwwhbbfvbffzpzlllrzlrrbnnrngrnrpnnsszbbqffpsffhfshfhzzqhhcgcgfggzmmdllthhrhnrrwggdqdsstccqllflmflfddjwjzjffvjjfgjgdgbdgdngnpgpnpffsnsjnnbbjdbjbtbmmbrrlbbqmqpqrprjjrbbvnbbzvvcwwlfwfggmhhdhsdhsdhshhqfhfrhhqlqttffpmmjzjqjggqzzdfzflfsllshhvjvfvbfvbbjljhhzrzqqszqzsqqswswbsbzszgzdgzzhjzhhvffhthvtthltthghzhvvjttczttlssvvgjjmsjstjjrfjjhbjbnjbjddqrddnbdnbnwbnbqbmqqgtgqtttcmmqbqrrgrrsrszssvpsvvjqjttjpjwwmwfwttczttgccwhcwwrzwzbwwqbqmqnmqqnfnmmmzdmzmpmssdpsslbbmgmbmlmnlldlccvzzlrzzqbqfqlflwlvlhhtrtcttgnnqhnqqtjjphjhwjhwhpwwvdvfddmndncnppcffhllfvfdfllhgslvtsqhtlfdflcjfmqbnctnfnwqrlqbzrcbvldrffcptsgslqcszqcfdvtpggvdqblwcgmdjqrpjdhtrmvrfrzznspqlfhnjsppbpjdggcwjwprpnlnntgfgmflctqphdmzfvpzzmbzmvrqdgchzmdvjdzmfsslpqvhpgznmpspjpdmlfwwjbbwqbfthghclldpmnsbcwlzswrsnfzbdzpcnrrpspdpfqhvmtfjlppqtphvzzqrwhzccnrgrtgfbfgtwvlwsmcvzmqmhsvztmmvpjzfwzgfwntbrsfthdgrcmgtdsvzcllmcshrlqldrvrnmdgbwttmhczvscrdvfgdvrhfvlghhsfbmrptbwmpnvtsrjlpjlbmmjzwwzbdtjlqqdczqgpzfjslccrcrblhplndblghchczbjjfzlsvvrqhvgdsncgpjhjlprhfhswwbmrnszqzhhlrbqpphvgtfsgmdpjwgcmqnvfdhrqmbspjpdrtdbqnbmbpgqwgmltqwrjprvsfjsmpldcqqbvmfhgzltzfvhlnfdqrphzzjrbdvnnjspvnlnnsdzvgqsqztndjpmnbqtwnpzmmfhsswwnnwwlbnpgbrhzchbnsrwwpprhntngsjzvssttqwfvjrdddtfpgtqqzcwljzmdjtgzdqjjvbqgdttdgvqvlfdsgcjhsmdmwrwdcqdflpfjbfzsvjrzrhhcnvcjblwcdvtbgfhfgcwrcjsrzcdrfwtvdqrghdtrjgdmhrfcsnwwwdpvjtpzdqfgrlmrqscjbfgdbgvflhvdjmnmslvsbcbgwplgqljmlzpgrfjwmvqfwmwrhnmdjhdwgjrngvccrbzmhcqthvvtdtmfqvfczhqbfgzgrmdtprznfzjtrcwqgztchtdmzmnwbfbnbttbvzsflcpsjshgphfdlvhdrcpsqnhjjggbnsqrfpwsdznzcwjbcswwndzbpdnfcbdrfgrmqzvtjttltbntznmqfsmqlgqvlqnrvgrnggslqhbplmgpzwlfzbvwdvrchsnhrnvgmzjdprvvspltcdzmdnlgtmrwnwpdndpdqjltcnmsggrvbprslqhfgmzqtppdpsjcmmbvfgmbpdnwdcgnssfgjhzhrjljdwhrzznscndgbscdmbbtbrnzbqzvcjgjgljbjlrrvdhjdllsnjzhwlmjslghrqplwjwssbzzpdzdfhhsqctlcddnfnnvbcwpdvzdcsgcqpctsjtdtnzpggpzsrrhfjtthqcqhtvwzltbdvdnbgwlppblwzjsqqbcpcrthhrhdnzhdnflqlvbzmcjfcrbmgdgqptfqfbmlfbblqdfmnwgvbdhmcmtmvtggqstjpwhvzjhbgpblmdrnggvrvphbglqgfcphmrgfmrwcdchtwfllqwsnbqttwdcvrwgzjfztmcffppqtmnwpgcrgwtjbdtjlmnpmvlzndljglzblwdrggqvbbfvqcbcbpqttrmqlcqnqvrfqsnlpmwlcgfwfcqpgmszfccbqtcqfwlwqrjjhrdbjqvdmfzjgncjqgqbthpgjgbfdvltbhpnbjqqwrsczrthfhmlzjjjgsjtsvgmwfsjngzfqdqzfhvwjrswvnqvsvvsjdbwlwdcsszdngmmhnnqsgvsrvpnndghrwgzztqczvhcrzdpqtrmrnfsfrlpdnbbtshfhplzqvdvzdvwhwsbpnbzlvcbgptdszjlcgfdzchjcsvhzdljvgpwstzwnssvhztcptnhslggnrschvfnmhcnjvldthtfpqzdvltfgnmtgvlrljhwqdzqfmfblstvfnpfcdsqslrqbztrbfzmsfjtjwhlzfnhrvpfqfqvtdllrvchmqphgljwcspgpwsdwqfdhsqhsflpbcbjjmjrfjrqrqfqcqzqsqcnqhfgsclfnfzblfdhphrvqdpvcqmllrcdnrlwqbrgqsbfqqllcvmglntjwcsjljgntmmldscndfdjcqpwbqpbmfjsgwfwcqbqbbhhgprlbzmvdfjcsmsqvhfhmgrhnwpslztmwbhdgrfzfcmwjswpbpzwstfbfmgwtprmptzjwtrqthrqwgslnmtlfgnvgpwvsfwthtrgwfbnnnwmdcfrpqqztplscvfnfpfwwdnfnzjccnhswwlcrrdqfhvsrnvcdrwmjswzggscplggbwgndsbntqvtrjbmbzrnbbmdjvwrmmtrmfjjhnvrcjcbqlhlthbvtjjczddblbbttmmzgdqmtdqswjdwbjhsrjbvdtqzqdbhhgbttgmgwfgfpczpqpfsddgslltwsvngwbwfbfcdzlqghwdbfzzldjpwpmpjmslwnwbrjjvwcsjgdzjwrrwnvgvrqlgjhwvrgnczspfplhfbtdpbpfqmhbvmcqdgrrjfslzgsqfpwrrrmjdtgbslddwvddrbmrdsdhhnlwsncrmnglrrpvtbrfvjbdmcpgphcdfwnfcglvmlbslttpmjnspqhnmbcqgmncfjjpdfjqhggnswbgppjhllscrvtmtmmbwbpgddtzblscntrmccdpzdnllqpvfdpfpwwvnnbjlzphvqwffwsjmbtllctrjmllwscmldcdrpfrzrqlpwbjwfgmnshzqzgdjqhcwtsqlsjffvzcpnrzmvtlzlgwvrrjtdbcnddbhjgqqzrvhplrbsrwgscjnfmhbcnpdcjqrltgdzzzzbqtsspbcdssbjrzfqdgvhmgdzsjdsqcfwbgrnhrlzgpjmhctqdccmvqzddmcptsjgtfshprqmslvtmtrprfsngrnnpnrccrvnrvcwzrbbnbghlwvcncgzglnqthchhsnzlfrcggdptvwlrbnfwgjpflgrcfzhhgffwcbhwlsdmvmsvvzvdcrlvlnstgz";

	public Day6b(String input) {
		for (int index = 0; index < input.length(); index++) {
			String a = input.charAt(index) + "";
			String b = input.charAt(index + 1) + "";
			String c = input.charAt(index + 2) + "";
			String d = input.charAt(index + 3) + "";
			String e = input.charAt(index + 4) + "";
			String f = input.charAt(index + 5) + "";
			String g = input.charAt(index + 6) + "";
			String h = input.charAt(index + 7) + "";
			String i = input.charAt(index + 8) + "";
			String j = input.charAt(index + 9) + "";
			String k = input.charAt(index + 10) + "";
			String l = input.charAt(index + 11) + "";
			String m = input.charAt(index + 12) + "";
			String n = input.charAt(index + 13) + "";

			if (a.equals(b) || a.equals(c) || a.equals(d) || a.equals(e) || a.equals(f) || a.equals(g) || a.equals(h)
					|| a.equals(i) || a.equals(j) || a.equals(k) || a.equals(l) || a.equals(m) || a.equals(n)) {
				continue;
			}

			if (b.equals(c) || b.equals(d) || b.equals(e) || b.equals(f) || b.equals(g) || b.equals(h) || b.equals(i)
					|| b.equals(j) || b.equals(k) || b.equals(l) || b.equals(m) || b.equals(n)) {
				continue;
			}

			if (c.equals(d) || c.equals(e) || c.equals(f) || c.equals(g) || c.equals(h) || c.equals(i) || c.equals(j)
					|| c.equals(k) || c.equals(l) || c.equals(m) || c.equals(n)) {
				continue;
			}

			if (d.equals(e) || d.equals(f) || d.equals(g) || d.equals(h) || d.equals(i) || d.equals(j) || d.equals(k)
					|| d.equals(l) || d.equals(m) || d.equals(n)) {
				continue;
			}

			if (e.equals(f) || e.equals(g) || e.equals(h) || e.equals(i) || e.equals(j) || e.equals(k) || e.equals(l)
					|| e.equals(m) || e.equals(n)) {
				continue;
			}

			if (f.equals(g) || f.equals(h) || f.equals(i) || f.equals(j) || f.equals(k) || f.equals(l) || f.equals(m)
					|| f.equals(n)) {
				continue;
			}

			if (g.equals(h) || g.equals(i) || g.equals(j) || g.equals(k) || g.equals(l) || g.equals(m) || g.equals(n)) {
				continue;
			}

			if (h.equals(i) || h.equals(j) || h.equals(k) || h.equals(l) || h.equals(m) || h.equals(n)) {
				continue;
			}

			if (i.equals(j) || i.equals(k) || i.equals(l) || i.equals(m) || i.equals(n)) {
				continue;
			}

			if (j.equals(k) || j.equals(l) || j.equals(m) || j.equals(n)) {
				continue;
			}

			if (k.equals(l) || k.equals(m) || k.equals(n)) {
				continue;
			}

			if (l.equals(m) || l.equals(n)) {
				continue;
			}

			if (m.equals(n)) {
				continue;
			}

			System.out.println("Found start-of-packet marker " + a + b + c + d + " at location: " + (index + 14));
			break;
		}
	}

	public static void main(String[] args) throws IOException {
		new Day6b(example1);
		new Day6b(example2);
		new Day6b(example3);
		new Day6b(example4);
		new Day6b(example5);
		new Day6b(real);
	}
}

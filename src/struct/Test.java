package struct;


public class Test {
	public static void main(String[] args) {
		DiskMap<String, String> map = new DiskMap<>("map");
		map.put("diretor", "tayob");
		map.put("founder", "neil richard");
		
		System.out.println(map.get("diretor"));
	}
}

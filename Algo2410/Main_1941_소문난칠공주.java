package Algo2410;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_1941_소문난칠공주 {
	
	static class RC{
		int r;
		int c;

		RC(int r, int c){
			this.r =  r;
			this.c = c;

		}
	}
	static boolean[][] map = new boolean[5][5];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean[][] pick7;
	static int som;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<RC> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				if(str.charAt(j) == 'S') {
					map[i][j] = true;
				}
			}
		}
		
		pick7 = new boolean[5][5];
		comb(0, 0, 0);
		
		System.out.println(som);

	}
	//전체 칸에서 임의로 7칸 고르기 -> 7칸이 인접한지 확인: isAdjacent()
	static void comb(int idx, int num, int numS) {
		if(7 - num < 4 - numS) return;
		
		if(num == 7) {
			if(numS < 4) return;
			if(isAdjacent()) som++;
			return;
		}
		
		
		for (int i = idx; i < 25; i++) {
			int r = i/5;
			int c = i%5;
            pick7[r][c] = true;
            comb(i + 1, num + 1, numS + (map[r][c] ? 1 : 0));
            pick7[r][c] = false;
        }		
	}

	static boolean isAdjacent() {
		boolean[][] visited = new boolean[5][5];
        	Queue<RC> que = new ArrayDeque<>();
        	//고른 7명 중 시작점 찾기 -> que에 저장
	        a: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(pick7[i][j]) {
					que.offer(new RC(i, j));
					visited[i][j] = true;
					break a;
				}
			}
		}
        	//시작점부터 인접 bfs -> 모두 인접하면 cnt == 7
        	int cnt = 1;
        	while(!que.isEmpty()) {
        		RC now = que.poll();
        		for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				
				if(!check(nr, nc)) continue;
				if(!pick7[nr][nc]) continue;
				if(visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				cnt++;
				que.offer(new RC(nr, nc));
			}
		}
	        return cnt == 7;
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < 5 && c >= 0 && c < 5;
	}

}

package com.hepsiburada.main;

import java.util.Scanner;
import org.apache.commons.lang3.tuple.Pair;
import com.hepsiburada.model.Position;
import com.hepsiburada.model.Space;
import com.hepsiburada.util.MethodUtil;;

/**
 * @author adil
 *
 */
public class MainClass {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		Space space = MethodUtil.getSpaceValue(scanner);

		Pair<Position, String> data = null;

		for (;;) {
			data = MethodUtil.getData(scanner);

			Position position = MethodUtil.calcualatePosition(space, data.getLeft(), data.getRight());

			System.out.println(position.getX() + " " + position.getY() + " " + position.getHead());

		}


	}

}

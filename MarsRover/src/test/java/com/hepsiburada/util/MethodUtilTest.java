package com.hepsiburada.util;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import com.hepsiburada.constant.Constant;
import com.hepsiburada.exception.PositionException;
import com.hepsiburada.exception.WrongCommand;
import com.hepsiburada.exception.WrongStartPositionException;
import com.hepsiburada.model.Position;
import com.hepsiburada.model.Space;

public class MethodUtilTest {

	private static Space space;

	private static Position position;

	private static String command;

	private static String positionBiggerCommand;
	
	private static String positionLowerCommand;

	@Before
	public void init() {

		createSpace();

		createPosition();

		command = "LMLMLMLMM";

		positionBiggerCommand = "LMLMLMLMMMMMMMM";
		
		positionLowerCommand = "LMMMMMMM";
	}

	private void createPosition() {

		position = new Position();

		position.setX(1);

		position.setY(2);

		position.setHead("N");

	}

	private void createSpace() {

		space = new Space();

		space.setX(5);

		space.setY(5);

	}

	@Test
	public void getSpaceValueTest() {

		String input = "5 5";

		InputStream in = new ByteArrayInputStream(input.getBytes());

		Scanner scanner = new Scanner(in);

		Space space = MethodUtil.getSpaceValue(scanner);

		assertEquals(space.getX(), Integer.parseInt(StringUtils.substringBefore(input, Constant.SEPARATOR)));

		assertEquals(space.getY(), Integer.parseInt(StringUtils.substringAfter(input, Constant.SEPARATOR)));

	}

	@Test(expected = WrongStartPositionException.class)
	public void getDataWrongPositionTest() throws Exception {

		String input = "5 5";

		InputStream in = new ByteArrayInputStream(input.getBytes());

		Scanner scanner = new Scanner(in);

		MethodUtil.getData(scanner);

	}

	@Test(expected = WrongStartPositionException.class)
	public void getDataNullPositionTestOne() throws Exception {

		String input = "";

		InputStream in = new ByteArrayInputStream(input.getBytes());

		Scanner scanner = new Scanner(in);

		MethodUtil.getData(scanner);

	}

	@Test(expected = WrongCommand.class)
	public void getDataWrongCommandTest() throws Exception {

		InputStream wrongCommand = MethodUtilTest.class.getResourceAsStream("WrongCommand.txt");

		Scanner scanner = new Scanner(wrongCommand);

		MethodUtil.getData(scanner);

	}

	@Test
	public void getData() throws Exception {

		InputStream is = MethodUtilTest.class.getResourceAsStream("SuccessCommand.txt");

		Scanner scanner = new Scanner(is);

		Pair<Position, String> data = MethodUtil.getData(scanner);

		assertEquals("LMLMLMLMM", data.getRight());

		assertEquals(1, data.getLeft().getX());

		assertEquals(2, data.getLeft().getY());

		assertEquals("N", data.getLeft().getHead());

	}

	@Test
	public void calcualatePositionTest() throws PositionException {

		Position lastPosition = MethodUtil.calcualatePosition(space, position, command);

		assertEquals(1, lastPosition.getX());

		assertEquals(3, lastPosition.getY());

		assertEquals("N", lastPosition.getHead());

	}

	@Test(expected = PositionException.class)
	public void calcualatePositionBiggerExceptionTest() throws PositionException {

		MethodUtil.calcualatePosition(space, position, positionBiggerCommand);
	}

	@Test(expected = PositionException.class)
	public void calcualatePositionLowerExceptionTest() throws PositionException {

		MethodUtil.calcualatePosition(space, position, positionLowerCommand);
	}
	
}

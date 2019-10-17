package com.hepsiburada.util;

import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import com.hepsiburada.constant.Constant;
import com.hepsiburada.constant.Directions;
import com.hepsiburada.constant.ErrorMessage;
import com.hepsiburada.exception.PositionException;
import com.hepsiburada.exception.WrongCommand;
import com.hepsiburada.exception.WrongStartPositionException;
import com.hepsiburada.model.Position;
import com.hepsiburada.model.Space;

public class MethodUtil {

	private static final Logger LOGGER = Logger.getLogger(MethodUtil.class.getName());

	private MethodUtil() {

	}

	/**
	 * @param scanner
	 * @return
	 * @throws Exception
	 */
	public static Pair<Position, String> getData(Scanner scanner) throws Exception {

		Position position = getPositionValue(scanner);

		String command = getCommand(scanner);

		return Pair.of(position, command);
	}

	/**
	 * @param scanner
	 * @return
	 */
	public static Space getSpaceValue(Scanner scanner) {

		LOGGER.info("Please Enter Space Value");

		Space space = new Space();

		String platea = scanner.nextLine();

		String xValue = StringUtils.substringBefore(platea, Constant.SEPARATOR);

		String yValue = StringUtils.substringAfter(platea, Constant.SEPARATOR);

		if (!StringUtils.isBlank(xValue)) {
			space.setX(Integer.parseInt(xValue));
		}

		if (!StringUtils.isBlank(yValue)) {
			space.setY(Integer.parseInt(yValue));
		}

		return space;

	}

	/**
	 * @param space
	 * @param position
	 * @param command
	 * @return
	 * @throws PositionException 
	 */
	public static Position calcualatePosition(Space space, Position position, String command) throws PositionException {

		for (int i = 0; i < command.trim().length(); i++) {

			if (Constant.MOVE.equals(String.valueOf(command.charAt(i)))) {

				position = positionUpdate(position);

				checkInSpace(position, space);

			} else {

				position.setHead(findNewPosition(position, String.valueOf(command.charAt(i))));

			}

		}

		return position;
	}

	/**
	 * @param position
	 * @return
	 */
	public static Position positionUpdate(Position position) {

		if (Constant.NORTH.equals(position.getHead())) {

			position.setY(position.getY() + Constant.MOVE_SIZE);

		} else if (Constant.SOUTH.equals(position.getHead())) {

			position.setY(position.getY() - Constant.MOVE_SIZE);

		} else if (Constant.WEST.equals(position.getHead())) {

			position.setX(position.getX() - Constant.MOVE_SIZE);

		} else if (Constant.EAST.equals(position.getHead())) {

			position.setX(position.getX() + Constant.MOVE_SIZE);

		}

		return position;

	}

	/**
	 * @param scanner
	 * @return
	 * @throws WrongCommand
	 */
	private static String getCommand(Scanner scanner) throws WrongCommand {

		LOGGER.info("Please Enter Command : ");

		String command = scanner.nextLine();

		if (!checkCommand(command)) {
			throw new WrongCommand(ErrorMessage.WRONG_COMMAND_MESSAGE);
		}

		return command;
	}

	/**
	 * @param command
	 * @return
	 */
	private static boolean checkCommand(String command) {

		return !StringUtils.isBlank(command) && StringUtils.containsOnly(command, Constant.COMMANDS);

	}

	/**
	 * @param scanner
	 * @return
	 * @throws WrongStartPositionException
	 */
	private static Position getPositionValue(Scanner scanner) throws WrongStartPositionException {

		LOGGER.info("Please Enter Start Position Value");

		String startPosition = null;

		Position position = new Position();

		try {
			startPosition = scanner.nextLine();
		} catch (Exception e) {
			throw new WrongStartPositionException(ErrorMessage.POSITION_NULL);
		}

		if (!StringUtils.isBlank(startPosition)) {

			String[] startPositionArray = startPosition.split(Constant.SEPARATOR);

			if (startPositionArray.length == 3) {

				position.setX(Integer.parseInt(startPositionArray[0]));

				position.setY(Integer.parseInt(startPositionArray[1]));

				position.setHead(startPositionArray[2]);

			} else {
				throw new WrongStartPositionException(startPosition);
			}

		}

		return position;
	}

	/**
	 * @param position
	 * @param command
	 * @return
	 */
	private static String findNewPosition(Position position, String command) {

		Map<String, String> coordinat = Directions.getCoordinat();

		return coordinat.get(position.getHead() + command);
	}

	/**
	 * @param position
	 * @param space
	 * @throws PositionException 
	 */
	private static void checkInSpace(Position position, Space space) throws PositionException {

		if (position.getX() > space.getX() || position.getY() > space.getY()) {

			throw new PositionException(ErrorMessage.BIG_POSITION);
		}

		if (position.getX() < 0 || position.getY() < 0) {

			throw new PositionException(ErrorMessage.BIG_POSITION);
		}

	}

}

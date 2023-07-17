package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.model.ErrorCode.ERR_FAILED_TO_CONVERT_INTEGER_LIST;
import static lotto.model.ErrorCode.ERR_NOT_INTEGER;

public class InputView {
    public static int getInt() {
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERR_NOT_INTEGER, e);
        }
    }

    public static List<Integer> getIntegerList() {
        try {
            String input = Console.readLine();
            List<String> splintedInput = List.of(input.split(","));
            return splintedInput.stream().map(Integer::parseInt).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException(ERR_FAILED_TO_CONVERT_INTEGER_LIST, e);
        }
    }
}

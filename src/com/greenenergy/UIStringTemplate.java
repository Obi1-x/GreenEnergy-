package com.greenenergy;

public class UIStringTemplate {
    public static final String INPUT_ERROR_PROMPT = "Input should be a number (1, 2 or 3)";
    public static final String INPUT_ERROR_PROMPT_2 = "Input should a number (1, 2, 3 or 4)";
    public static final String GEN_NOT_FOUND_PROMPT = "Generator not yet added. Press 1 to create it.";
    public static final String[] MAIN_MENU = { "\nWelcome to Chester Energy Generators Limited \n",
                                               "1. Create generator",
                                               "2. Get generators",
                                               "3. Get a generator",
                                               "Enter a number to perform the correspond function:"
                                             };

    public static final String[] GEN_SELECT_MENU = { "\n1. Solar Pv module.",
                                                     "2. Solar Pv array",
                                                     "3. Air sourced heat pump",
                                                     "4. Ground sourced heat pump",
                                                     "Select the generator type to create:"};

    public static final String[] GEN_FILTER_MENU = { "\n1. All generators.",
                                                     "2. All generators sorted by price",
                                                     "3. All Solar PV modules.",
                                                     "4. All Heat pumps.",
                                                     "Select the search criteria"};
}

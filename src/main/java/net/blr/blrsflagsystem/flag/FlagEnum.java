package net.blr.blrsflagsystem.flag;

public class FlagEnum {

    public enum Flag {
        FRANCE("FRANCE", "ҁ"),
        USA("USA", "ь"),
        UK("UK", "ҁ");

        private final String name;
        private final String char_emoji;

        Flag(String flagName, String char_emoji) {
            this.name = flagName;
            this.char_emoji = char_emoji;
        }

        public String getName() {
            return name;
        }

        public String getCharEmoji() {
            return char_emoji;
        }
    }

}

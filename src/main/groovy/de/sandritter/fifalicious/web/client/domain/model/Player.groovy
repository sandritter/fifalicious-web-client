package de.sandritter.fifalicious.web.client.domain.model

class Player {
    Long playerId
    String lastName
    String firstName
    Date last_update
    List<Stroke> strokes

    int getActiveAmountOfStrokes() {
        strokes.findAll() {
            it.isActive == true
        }.size()
    }

    String getStrikeRange() {
        int amountOfStrokes = getActiveAmountOfStrokes()
        if (amountOfStrokes > 0) {
            String strikeRange = ''
            switch (amountOfStrokes) {
                case 1:
                    strikeRange = 'one-of-five'
                    break;
                case 2:
                    strikeRange = 'two-of-five'
                    break;
                case 3:
                    strikeRange = 'three-of-five'
                    break;
                case 4:
                    strikeRange = 'four-of-five'
                    break;
                case 5:
                    strikeRange = 'five'
                    break;
            }
            return strikeRange
        }
        return 'five'
    }
}

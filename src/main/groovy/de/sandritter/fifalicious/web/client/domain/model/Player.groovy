package de.sandritter.fifalicious.web.client.domain.model

class Player {
    Long playerId
    String lastName
    String firstName
    Date last_update
    List<Stroke> strokes

    int getActiveAmountOfStrokes() {
        strokes.findAll() {
            it.isActive
        }.size()
    }

    String getStrikeRange() {
        int amountOfStrokes = getActiveAmountOfStrokes()
        String strikeRange = ''
        switch (amountOfStrokes) {
            case 0:
                strikeRange = 'zero'
                break
            case 1:
                strikeRange = 'one'
                break
            case 2:
                strikeRange = 'two'
                break
            case 3:
                strikeRange = 'three'
                break
            case 4:
                strikeRange = 'four'
                break
            case 5:
                strikeRange = 'five'
                break
            default:
                strikeRange = 'five'
        }
        return strikeRange
    }
}

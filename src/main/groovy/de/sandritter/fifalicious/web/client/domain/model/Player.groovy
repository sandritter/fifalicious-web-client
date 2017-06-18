package de.sandritter.fifalicious.web.client.domain.model

class Player {
    Long reference
    String lastName
    String firstName
    Date lastUpdate
    List<Stroke> strokes

    int getActiveAmountOfStrokes() {
        strokes.findAll() {
            it.isActive
        }.size()
    }

    String getStrikeRange() {
        int amountOfStrokes = getActiveAmountOfStrokes()
        String strikeRange
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

    String getCombinedShortName() {
        firstName + ' ' + lastName.subSequence(0, 1) + '.'
    }

    String getColorCode() {
        int amountStrokes = getActiveAmountOfStrokes()
        String colorCode
        switch (amountStrokes) {
            case 0:
                colorCode = '#6fdb6f'
                break
            case 1:
                colorCode = '#6fdb6f'
                break
            case 2:
                colorCode = '#6fdb6f'
                break
            case 3:
                colorCode = '#F7E211'
                break
            case 4:
                colorCode = '#F7E211'
                break
            case 5:
                colorCode = '#562F19'
                break
            default:
                colorCode = '#562F19'
        }
        return colorCode
    }
}

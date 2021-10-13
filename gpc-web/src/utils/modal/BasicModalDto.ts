export interface BasicModalDto {
    visible: boolean,
    title: string,
    style: Object,
    onClose?: Fn,
    fullMode: boolean,
    onFull?: Fn,
}

export function creatorDto(conf?: Object) {
    return {...DefaultVal, ...conf} as BasicModalDto
}

const DefaultVal = {
    style: {"minWidth": "700px"},
    title: "Title " + new Date(),
    visible: false,
    fullMode: false,
}



export const ToUnixTime = (time: number | string) : number => {
    return new Date(time).getTime() / 1000;
}

export const FromUnixTime = (unixTime: number): string => {
    const date = new Date(unixTime * 1000);
    return date.toISOString().split("T")[0];
}
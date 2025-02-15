import {Priority} from "../type/EnumType.tsx";

export const rtnPrimaryBadge = (primary: string): React.JSX.Element => {
    if(primary === Priority.HIGH) return <span className="badge text-bg-danger">HIGH</span>;
    if(primary === Priority.MEDIUM) return <span className="badge text-bg-warning">MEDIUM</span>;
    if(primary === Priority.LOW) return <span className="badge text-bg-success">LOW</span>;
    return <span className="badge text-bg-secondary">NONE</span>;
}
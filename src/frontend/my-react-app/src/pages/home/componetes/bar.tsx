import "./bar.css";
import { dashboard } from "../../../types/dashborad";
import type { barProps, Layoutchanges, Pages } from "../../../utils/interfaces";
interface bartelas extends Pages { }
interface lchangeayout extends Layoutchanges { }
function Bar({ mudarTela, mudarlayout }: barProps) {
    return (
        <nav className="dashboard">
            <ul>
                {dashboard.map((item, index) => (
                    <li key={index} onClick={() => mudarlayout(item)}>{item}</li>
                ))}
            </ul>
        </nav>
    )
}

export default Bar;
import { dashboard } from "../../../types/dashborad.ts";
import type { Layoutchanges } from "../../../utils/interfaces.ts";
import "./bar.css";
function Bar({ mudarlayout }: Layoutchanges) {
  return (
    <nav className="dashboard">
      <ul>
        {dashboard.map((item, index) => (
          <li key={index} onClick={() => mudarlayout(item)}>
            {item}
          </li>
        ))}
      </ul>
    </nav>
  );
}

export default Bar;

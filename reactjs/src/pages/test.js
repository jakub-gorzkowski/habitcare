import * as React from "react";

function ProfileIcon() {
  return <div className="shrink-0 rounded-full bg-neutral-200 h-[39px] w-[39px]" />;
}

function MenuItem({ icon, label }) {
  return (
    <div className="flex gap-5 justify-between whitespace-nowrap">
      <img loading="lazy" src={icon} className="shrink-0 w-5 aspect-[1.05]" alt="" />
      <div className="my-auto">{label}</div>
    </div>
  );
}

const menuItems = [
  { icon: "https://cdn.builder.io/api/v1/image/assets/TEMP/918fc3be32a11d9f77865085802bea921ae2636184a7a5412eeaf3877696a2cf?apiKey=cd2e064595044c2c9413206bd79a5e35&", label: "Friends" },
  { icon: "https://cdn.builder.io/api/v1/image/assets/TEMP/1febe07c5e2f5a39d36319adde892293cb2eb23e8741498d3e596bf3b5632720?apiKey=cd2e064595044c2c9413206bd79a5e35&", label: "Streaks" },
  { icon: "https://cdn.builder.io/api/v1/image/assets/TEMP/7d965f412e3457a34d8d5a0433add6c406e18a40238aa7be0d2bfe307139e55b?apiKey=cd2e064595044c2c9413206bd79a5e35&", label: "Mood journal" },
  { icon: "https://cdn.builder.io/api/v1/image/assets/TEMP/55c28dde180316e1929c87c1162d1cf8dba676109df9ef375db553dbb23d29c1?apiKey=cd2e064595044c2c9413206bd79a5e35&", label: "Progress" },
  { icon: "https://cdn.builder.io/api/v1/image/assets/TEMP/d92881bca3bacc619181b889e1c4306443928207ba771c2e15fec1d08474c027?apiKey=cd2e064595044c2c9413206bd79a5e35&", label: "Calender" },
];

function MyComponent() {
  return (
    <div className="flex flex-col items-start py-12 pr-20 pl-10 mx-auto w-full text-sm text-white bg-gray-900 max-w-[480px]">
      <header className="flex gap-5">
        <ProfileIcon />
        <div className="my-auto">My profile</div>
      </header>
      <nav className="mt-8 ml-3.5">
        <MenuItem icon="https://cdn.builder.io/api/v1/image/assets/TEMP/498146315a40cfee5fef4ac36812d94fca345c430bf9d16c728c642d454d9385?apiKey=cd2e064595044c2c9413206bd79a5e35&" label="Habits" />
      </nav>
      <div className="mt-12 text-xs">Menu</div>
      <ul className="mt-10">
        {menuItems.map((item, index) => (
          <li key={index} className={`mt-${index === 0 ? 9 : 11} ml-${index < 2 ? 3.5 : 5}`}>
            <MenuItem icon={item.icon} label={item.label} />
          </li>
        ))}
      </ul>
      <footer className="flex gap-5 justify-between mt-96 whitespace-nowrap">
        <img loading="lazy" src="https://cdn.builder.io/api/v1/image/assets/TEMP/9a288029d4f6eac7cb0a7031747e0cc7ba060d04eb39a6f9b1593c27b8ab52ef?apiKey=cd2e064595044c2c9413206bd79a5e35&" className="shrink-0 aspect-[1.28] w-[37px]" alt="" />
        <div className="my-auto">Logout</div>
      </footer>
    </div>
  );
}
export default MyComponent;
import React, { ReactNode, useEffect, useState } from 'react';

interface DadaistWrapperProps {
  children: ReactNode;
  className?: string;
  onClick?: (e: React.MouseEvent) => void;
}

const DISCORDANT_HUES = [
    '#f59e0b', // Amber
    '#ea580c', // Deep Orange
    '#d97706', // Copper
    '#fef08a', // Yellow White
    '#2dd4bf', // Occasional Cyan spark
];

export const DadaistWrapper: React.FC<DadaistWrapperProps> = ({ children, className = '', onClick }) => {
    // Use useState initializer to prevent double render/layout shift
    const [randomValues] = useState(() => ({
        tilt: (Math.random() * 3) - 1.5,
        shiftX: (Math.random() * 8) - 4,
        shiftY: (Math.random() * 8) - 4,
        accentColor: DISCORDANT_HUES[Math.floor(Math.random() * DISCORDANT_HUES.length)]
    }));

    const { tilt, shiftX, shiftY, accentColor } = randomValues;

    const style = {
        '--random-tilt': `${tilt}deg`,
        '--random-shift-x': `${shiftX}px`,
        '--random-shift-y': `${shiftY}px`,
        '--random-accent': accentColor,
    } as React.CSSProperties;

    return (
        <div style={style} className={className} onClick={onClick}>
            <div style={{ transform: 'rotate(var(--random-tilt)) translate(var(--random-shift-x), var(--random-shift-y))' }} className="h-full w-full">
                {children}
            </div>
        </div>
    );
};

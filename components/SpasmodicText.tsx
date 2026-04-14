import React, { useState, useEffect } from 'react';

interface SpasmodicTextProps {
  text: string;
  className?: string;
}

const FONTS = [
  "'Space Mono', monospace",
  "'Crimson Text', serif",
  "'Rubik Glitch', cursive",
  "sans-serif",
  "serif",
  "monospace"
];

export const SpasmodicText: React.FC<SpasmodicTextProps> = ({ text, className = '' }) => {
  const [isHovered, setIsHovered] = useState(false);
  const [charStyles, setCharStyles] = useState<React.CSSProperties[]>([]);

  useEffect(() => {
    const styles = text.split('').map(() => ({
      fontFamily: FONTS[Math.floor(Math.random() * FONTS.length)],
      fontSize: `${Math.random() * 0.4 + 0.8}em`,
      fontWeight: Math.random() > 0.5 ? 'bold' : 'normal',
      transform: `rotate(${(Math.random() * 20) - 10}deg)`,
      display: 'inline-block',
      transition: 'all 0.2s ease-out'
    }));
    setCharStyles(styles);
  }, [text]);

  return (
    <span
      className={`inline-block ${className}`}
      aria-label={text}
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      {text.split('').map((char, i) => {
        // If it's a space, render it normally to preserve word breaks
        if (char === ' ') {
          return <span key={i}>&nbsp;</span>;
        }

        return (
          <span
            key={i}
            style={isHovered ? {
                display: 'inline-block',
                transition: 'all 0.2s ease-out'
            } : charStyles[i]}
          >
            {char}
          </span>
        );
      })}
    </span>
  );
};
